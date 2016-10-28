package com.ly.controller;

import com.ly.domain.User;
import com.ly.service.RibbonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yongliu on 10/28/16.
 */
@RestController
public class RibbonController {
  
  private static Logger logger = Logger.getLogger(RibbonController.class);
  
  @Autowired
  private RibbonService ribbonService;
  
  @GetMapping("/{id}")
  public User findById(@PathVariable Long id){
    logger.debug("Ribbon controller find user by id#" + id);
    return ribbonService.findById(id);
  }
}
