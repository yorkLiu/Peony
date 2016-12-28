package com.ly.netty.thread;

import com.ly.netty.handler.ServerHandler;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import static org.jboss.netty.channel.Channels.pipeline;

/**
 * Created by yongliu on 12/28/16.
 */
public class ServerThread implements Runnable {
  
  private static final NioServerSocketChannelFactory FACTORY = new NioServerSocketChannelFactory(
    Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
  
  private static final ServerBootstrap bootstrap = new ServerBootstrap(FACTORY);
  
  private static final ChannelPipelineFactory PIPELINE_FACTORY = new ChannelPipelineFactory() {
    @Override
    public ChannelPipeline getPipeline() throws Exception {
      ChannelPipeline pipeline = pipeline();
      pipeline.addLast("encode", new StringEncoder());
      pipeline.addLast("decode", new StringDecoder());
      pipeline.addLast("handler", new ServerHandler());
      return pipeline;
    }
  };

  static {
    bootstrap.setPipelineFactory(PIPELINE_FACTORY);
  }

  @Override
  public void run() {
    bootstrap.bind(new InetSocketAddress(8080));
  }
}
