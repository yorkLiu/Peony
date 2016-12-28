package com.ly.netty.main;

import com.ly.netty.thread.ServerThread;
import com.ly.netty.utils.ServerPullUtils;
import org.jboss.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by yongliu on 12/28/16.
 */
public class ServerMain {
  
  public static ConcurrentMap<String, Channel> channelMap = new ConcurrentHashMap<>();

  public static void main(String[] args) {
    try {
      //启动服务器
      ServerThread r = new ServerThread();
      Thread t = new Thread(r);
      t.setName("server thread");
      t.start();

      System.out.println("server start at localhost:8080");
      //推送消息
      ServerPullUtils.pullMsg();//这里是个while ture,模拟server不停给client反馈

    } catch (Exception e) {
      System.out.println("know exception on server: " + e.getMessage());
    }
    
  }
}
