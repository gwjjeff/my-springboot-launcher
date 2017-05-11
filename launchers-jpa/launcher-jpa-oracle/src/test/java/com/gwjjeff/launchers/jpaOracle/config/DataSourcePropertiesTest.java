package com.gwjjeff.launchers.jpaOracle.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by jeff on 2017/5/9.
 */
@ActiveProfiles("jpaOracle")
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourcePropertiesTest {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Test
    public void getDriverClassName() throws Exception {
        assertNotNull(dataSourceProperties.getDriverClassName());
    }

}