package com.ly.controller;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ly.domain.User;

import com.ly.repository.UserJapRepository;


/**
 * Created by yongliu on 10/28/16.
 *
 * @author   <a href="mailto:yong.liu@ozstrategy.com">Yong Liu</a>
 * @version  10/28/2016 14:51
 */
@RestController public class UserController {
  //~ Static fields/initializers ---------------------------------------------------------------------------------------

  private static Logger logger = Logger.getLogger(UserController.class);

  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Autowired private DiscoveryClient discoveryClient;

  @Autowired private UserJapRepository userJapRepository;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * findById.
   *
   * @param   id  Long
   *
   * @return  User
   */
  @GetMapping("/{id}")
  public User findById(@PathVariable Long id) {
    if (logger.isDebugEnabled()) {
      logger.debug("Find User by id#" + id);
    }

    User user = userJapRepository.findOne(id);

    if (logger.isDebugEnabled()) {
      logger.debug("User info: " + user);
    }


    return user;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * showInfo.
   *
   * @return  ServiceInstance
   */
  @GetMapping("/instance-info")
  public ServiceInstance showInfo() {
    if (logger.isDebugEnabled()) {
      logger.debug("Show service instance info");
    }

    ServiceInstance serviceInstance = this.discoveryClient.getLocalServiceInstance();

    if (logger.isDebugEnabled()) {
      logger.debug("The service instance info is: " + serviceInstance);
    }

    return serviceInstance;
  }
} // end class UserController
