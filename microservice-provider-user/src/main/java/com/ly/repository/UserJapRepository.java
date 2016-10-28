package com.ly.repository;

import com.ly.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yongliu on 10/28/16.
 */
@Repository
public interface UserJapRepository extends JpaRepository<User, Long> {
}
