package com.ly.service;

import com.ly.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yongliu on 10/28/16.
 */
@Service
public class RibbonService {
  
  @Autowired
  private RestTemplate restTemplate;
  
  public User findById(Long id){
    return restTemplate.getForObject("http://microservice-provider-user/" + id, User.class);
  }
}
