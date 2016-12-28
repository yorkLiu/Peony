package com.ly.netty.handler;

import com.ly.netty.main.ServerMain;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import java.net.InetSocketAddress;

/**
 * Created by yongliu on 12/28/16.
 */
public class ServerHandler extends SimpleChannelUpstreamHandler {

  @Override
  public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
    Channel channel = e.getChannel();
    if(channel != null){
      String host = ((InetSocketAddress) ctx.getChannel().getRemoteAddress()).getAddress().getHostAddress();
      int port = ((InetSocketAddress) ctx.getChannel().getRemoteAddress()).getPort();

      ServerMain.channelMap.put(host + ":" + port, channel);
    }

    System.out.println("Server receive message: " + e.getMessage());
    System.out.println(ServerMain.channelMap);
    
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
    System.out.println("Server has an error, details: " + e.getCause());
    e.getChannel().close();
    System.out.println("Closed the channel.");
  }

  @Override
  public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
    String host = ((InetSocketAddress) ctx.getChannel().getRemoteAddress()).getAddress().getHostAddress();
    int port = ((InetSocketAddress) ctx.getChannel().getRemoteAddress()).getPort();

    System.out.println("Server closed channel: [" + host + ":" + port + "]");
    
    // 移除无用连接
    try{
      ServerMain.channelMap.remove(host + ":" + port);
    }catch (Exception ex){
      //这里如果为了更高可用,可以将删除失败的加入一个队列里，后台启动一个cleanup的线程
    }
  }
}
