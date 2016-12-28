package com.ly.netty.utils;

import com.ly.netty.main.ServerMain;
import org.jboss.netty.channel.Channel;

/**
 * Created by yongliu on 12/28/16.
 */
public class ServerPullUtils {

  // 根据业务逻辑推送一些消息
  public static void pullMsg() {
    //这里为了方便测试,也是类似心跳推送
    while (true) {
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      //遍历所有维持链接的client,发送消息。忽略掉宕机的client
      for (String client : ServerMain.channelMap.keySet()) {
        Channel channel = ServerMain.channelMap.get(client);
        serverSendMsg(channel,client);
      }

    }
  }

  //发送hello 
  public static void serverSendMsg(Channel channel,String clientName) {
    String msg = "server say hello !" + clientName;
    if (channel != null && channel.isOpen() && channel.isConnected()
      && channel.isWritable()) {
      channel.write(msg);
    }
  }
}
