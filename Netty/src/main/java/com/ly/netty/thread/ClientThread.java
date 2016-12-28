package com.ly.netty.thread;

import com.ly.netty.handler.ClientHandler;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.handler.timeout.ReadTimeoutHandler;
import org.jboss.netty.util.HashedWheelTimer;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import static org.jboss.netty.channel.Channels.pipeline;

/**
 * Created by yongliu on 12/28/16.
 */
public class ClientThread implements Runnable {
  
  public static boolean isShortTcp = false;
  
  private static NioClientSocketChannelFactory FACTORY = new NioClientSocketChannelFactory(
    Executors.newCachedThreadPool(), Executors.newSingleThreadExecutor());
  
  public static final ClientBootstrap bootstrap = new ClientBootstrap(FACTORY);
  
  public static InetSocketAddress remoteServerAddress = new InetSocketAddress("127.0.0.1", 8080);
  
  private static HashedWheelTimer timer = new HashedWheelTimer();
  
  private static ReadTimeoutHandler timeoutHandler = new ReadTimeoutHandler(timer, 3);
  
  private static ChannelPipelineFactory CHANNEL_PIPELINE_FACTORY = new ChannelPipelineFactory() {
    @Override
    public ChannelPipeline getPipeline() throws Exception {
      ChannelPipeline pipeline = pipeline();
      pipeline.addLast("encode", new StringEncoder());
      pipeline.addLast("decode", new StringDecoder());
      pipeline.addLast("timeout", timeoutHandler);
      pipeline.addLast("handler", new ClientHandler());
      return pipeline;
    }
  };
  
  static {
    bootstrap.setPipelineFactory(CHANNEL_PIPELINE_FACTORY);
  }

  //静态化ChannelFuture,因为客户端只需要保持一个连接跟服务器
  public static ChannelFuture channelFuture;

  //客户端线程标识名
  private String name;

  public ClientThread(String name){
    this.name = name;
  }


  @Override
  public void run() {
    if(!isShortTcp){
      channelFuture = bootstrap.connect(remoteServerAddress);
      System.out.println("Client started done, return a long connect: " + channelFuture.hashCode());
    }
  }

  public String getName() {
    return name;
  }
}
