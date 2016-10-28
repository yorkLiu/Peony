package com.ly.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by yongliu on 10/27/16.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class EurekaClientApplication {
  public static void main(String[] args) {
    SpringApplication.run(EurekaClientApplication.class, args);
  }
}
