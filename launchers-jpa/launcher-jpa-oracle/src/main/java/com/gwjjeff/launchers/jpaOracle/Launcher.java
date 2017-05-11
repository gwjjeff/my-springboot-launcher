package com.gwjjeff.launchers.jpaOracle;

import com.gwjjeff.base.BaseDemoLauncher;
import com.gwjjeff.launchers.jpaOracle.dao.UserRepository;
import com.gwjjeff.launchers.jpaOracle.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Profile("jpaOracle")
@Component("jpaOracle_launcher")
@EnableTransactionManagement
//@Transactional("userTransactionManager")
public class Launcher extends BaseDemoLauncher {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void doRun(ApplicationArguments args) throws Exception {
        log.info("=============  JPA ORACLE LAUNCHER  ===============");

        userClean();
        userOpsDemo();
    }

    private void userClean() {
        userRepository.deleteAll();
    }

    private void userOpsDemo() {
        User user = new User();
        user.setName("John");
        user.setEmail("john@test.com");
        user.setAge(20);
        user = userRepository.save(user);

        log.info("User saved: {}", user);
    }
}