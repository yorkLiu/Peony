package com.ly.controller;

import com.ly.domain.User;
import com.ly.repository.UserJapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yongliu on 10/28/16.
 */
@RestController
public class UserController {
  
  @Autowired
  private DiscoveryClient discoveryClient;
  
  @Autowired
  private UserJapRepository userJapRepository;
  
  @GetMapping("/{id}")
  public User findById(@PathVariable Long id){
    User user  = userJapRepository.findOne(id);
    
    
    return user;
  }
  
  @GetMapping("/instance-info")
  public ServiceInstance showInfo(){
    ServiceInstance serviceInstance = this.discoveryClient.getLocalServiceInstance();
    return serviceInstance;
  }
}
