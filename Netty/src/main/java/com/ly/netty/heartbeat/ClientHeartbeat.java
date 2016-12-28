package com.ly.netty.heartbeat;

import com.ly.netty.thread.ClientThread;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;

/**
 * Created by yongliu on 12/28/16.
 */
public class ClientHeartbeat {
  
  private int frequency = 3000;
  
  public ClientHeartbeat(int frequency){
    this.frequency = frequency;
  }
  
  
  public void start(String clientName){
    while (true){
      try {
        Thread.sleep(frequency);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      
      if(ClientThread.isShortTcp){
        sendMsgByShortTcp(clientName);
      } else {
        sendMsgByLongTcp(clientName);
      }

    }
  }
  
  private void sendMsgByLongTcp(String clientName){
    if(ClientThread.channelFuture == null){
      return;
    }
    
    Channel channel = ClientThread.channelFuture.getChannel();
    if (channel != null && channel.isOpen() && channel.isConnected()
      && channel.isWritable()) {
      System.out.println("客户端使用长连接: " + channel + " 发送消息");
      String s = clientName + " say Hello";
      channel.write(s);
    }
  }
  
  private void sendMsgByShortTcp(String clientName){
    ChannelFuture channelFuture = ClientThread.bootstrap.connect(ClientThread.remoteServerAddress);
    channelFuture.awaitUninterruptibly();// 这里为了简便,调用此方法。更好的方法应该是加listener

    Channel channel = channelFuture.getChannel();
    if(channel != null && channel.isOpen() && channel.isConnected() && channel.isWritable()){
      System.out.println("客户端建立短连接: " + channel + " 发送消息");
      String s = clientName + " say Hello";
      channel.write(s);
    }
  }
  
  
}
