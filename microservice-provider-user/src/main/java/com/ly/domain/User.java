package com.ly.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Created by yongliu on 10/28/16.
 *
 * @author   <a href="mailto:yong.liu@ozstrategy.com">Yong Liu</a>
 * @version  10/28/2016 14:51
 */
@Entity
@Table(name = "user")
public class User {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private int age;

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id private Long id;

  private String username;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * getter method for age.
   *
   * @return  int
   */
  public int getAge() {
    return age;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for id.
   *
   * @return  Long
   */
  public Long getId() {
    return id;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * getter method for username.
   *
   * @return  String
   */
  public String getUsername() {
    return username;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for age.
   *
   * @param  age  int
   */
  public void setAge(int age) {
    this.age = age;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for id.
   *
   * @param  id  Long
   */
  public void setId(Long id) {
    this.id = id;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * setter method for username.
   *
   * @param  username  String
   */
  public void setUsername(String username) {
    this.username = username;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  java.lang.Object#toString()
   */
  @Override public String toString() {
    final StringBuffer sb = new StringBuffer("User{");
    sb.append("age=").append(age);
    sb.append(", id=").append(id);
    sb.append(", username='").append(username).append('\'');
    sb.append('}');

    return sb.toString();
  }
} // end class User
