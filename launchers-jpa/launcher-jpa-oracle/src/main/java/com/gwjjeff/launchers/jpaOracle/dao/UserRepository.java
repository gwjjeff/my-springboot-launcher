package com.gwjjeff.launchers.jpaOracle.dao;

import com.gwjjeff.launchers.jpaOracle.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
  extends JpaRepository<User, Integer> { }
