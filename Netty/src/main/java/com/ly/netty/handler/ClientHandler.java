package com.ly.netty.handler;

import com.ly.netty.thread.ClientThread;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.Timer;
import org.jboss.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

/**
 * Created by yongliu on 12/28/16.
 */
public class ClientHandler extends SimpleChannelUpstreamHandler {
  
  private static Timer timer = new HashedWheelTimer();
  
  private static TimerTask timerTask = new TimerTask() {
    @Override
    public void run(Timeout timeout) throws Exception {
      //定时器回调逻辑,即重连,并将重连后的future设置到客户端线程里
      ChannelFuture channelFuture = ClientThread.bootstrap.connect(ClientThread.remoteServerAddress);
      ClientThread.channelFuture = channelFuture;
    }
  };
  
  @Override
  public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
    System.out.println("Client receive msg: " + e.getMessage());
    
   if(!ClientThread.isShortTcp){
     System.out.println("Short TCP connect, channel: " + ctx.getName() + ", " + ctx.getChannel());
     e.getChannel().close();
   }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
    System.err.println("Client has a error ,Error cause:" + e.getCause());
    e.getChannel().close();//有异常则关闭连接
  }

  @Override
  public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
    //如果是长连接,才需要客户端断线重连
    if(!ClientThread.isShortTcp){
      //设定定时器,5秒后执行一次逻辑
      timer.newTimeout(timerTask, 5, TimeUnit.SECONDS);
    }
  }
}
