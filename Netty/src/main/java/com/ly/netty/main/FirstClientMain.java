package com.ly.netty.main;

import com.ly.netty.heartbeat.ClientHeartbeat;
import com.ly.netty.thread.ClientThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yongliu on 12/28/16.
 */
public class FirstClientMain {
  
  public static final int SEND_MILLISECOND = 3000;

  private static final ExecutorService service = Executors.newSingleThreadExecutor();
  public static void main(String[] args) {
    
    try {

      ClientThread client1 = new ClientThread("Client1");
      ClientThread.isShortTcp = true;
      service.submit(client1);

      ClientHeartbeat clientHeartbeat = new ClientHeartbeat(SEND_MILLISECOND);
      clientHeartbeat.start(client1.getName());

    }catch (Exception e){
      e.printStackTrace();
    }
    
    
  }
}
