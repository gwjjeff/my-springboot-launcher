package com.gwjjeff.launchers.jpaOracle.dao;

import com.gwjjeff.launchers.jpaOracle.config.JpaOracleConfig;
import com.gwjjeff.launchers.jpaOracle.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

/**
 * Created by jeff on 2017/5/9.
 */
@ActiveProfiles("jpaOracle")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional("userTransactionManager")
    public void whenCreatingUserThenCreated() {
        User user = new User();
        user.setName("John");
        user.setEmail("john@test.com");
        user.setAge(20);
        user = userRepository.save(user);

        assertNotNull(userRepository.findOne(user.getId()));
    }
}