package com.ly.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by yongliu on 10/27/16.
 *
 * @author   <a href="mailto:yong.liu@ozstrategy.com">Yong Liu</a>
 * @version  10/27/2016 17:16
 */

@RefreshScope @RestController public class ConfigClientController {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Value("${profile}")
  private String profile;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * hello.
   *
   * @return  String
   */
  @GetMapping("/hello")
  public String hello() {
    return this.profile;
  }
  
  @GetMapping("/info")
  public String getInfo(){
    return "this is a test";
  }

}
