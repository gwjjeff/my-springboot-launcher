package com.gwjjeff.launchers.redis;

import com.gwjjeff.base.BaseDemoLauncher;
import com.gwjjeff.launchers.redis.bean.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Component("redis_launcher")
@Slf4j
public class Launcher extends BaseDemoLauncher {

    private final RedisTemplate<String, UserInfo> userInfoRedisTemplate;

    @Resource(name="userInfoRedisTemplate")
    private ListOperations<String, UserInfo> userInfoListOps;

    @Autowired
    public Launcher(
            @Qualifier("userInfoRedisTemplate") RedisTemplate<String, UserInfo> userInfoRedisTemplate
    ) {
        this.userInfoRedisTemplate = userInfoRedisTemplate;
    }

    @Override
    public void doRun(ApplicationArguments args) throws Exception {
        log.info("=============  REDIS LAUNCHER  ===============");
        delUserInfo();
        leftPushUser();
        takeUser();
    }

    private void delUserInfo() {
        userInfoRedisTemplate.delete("UserInfo");
    }

    private void takeUser() {
        String key = "UserInfo";
        userInfoListOps.range(key, 0, 9).forEach((u) -> log.info("take user info: {}", u));
    }

    private void leftPushUser() {
        String key = "UserInfo";
        int size = 10;
        for(int i = 0; i < size; i++){
            UserInfo info = new UserInfo();
            info.setName("Tomy" + i);
            info.setAge(20 + i);
            info.setBirthday(new Date());
            info.setId(UUID.randomUUID().toString());
//            userInfoRedisTemplate.opsForList().leftPush(key, info);
            userInfoListOps.leftPush(key, info);
        }
        log.info("insert [{}] User success! {}'s size is: {}", size, key, userInfoListOps.size(key));
    }
}