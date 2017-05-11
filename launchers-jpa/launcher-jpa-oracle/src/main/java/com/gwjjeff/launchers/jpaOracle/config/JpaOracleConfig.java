package com.gwjjeff.launchers.jpaOracle.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * Created by jeff on 2017/5/9.
 */
@Profile("jpaOracle")
@Configuration
@EnableConfigurationProperties({DataSourceProperties.class, JpaProperties.class})
@EnableJpaRepositories(
        basePackages = "com.gwjjeff.launchers.jpaOracle.dao",
        entityManagerFactoryRef = "userEntityManagerFactory",
        transactionManagerRef = "userTransactionManager"
)
public class JpaOracleConfig {

    @Autowired
    private DataSourceProperties dataSourceProperties;
    @Autowired
    private JpaProperties jpaProperties;

    @Bean
    public DataSource userDataSource() {

        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(userDataSource());
        em.setPackagesToScan(
                "com.gwjjeff.launchers.jpaOracle.model");

        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform(jpaProperties.getDatabasePlatform());
        vendorAdapter.setShowSql(jpaProperties.isShowSql());
        vendorAdapter.setGenerateDdl(jpaProperties.isGenerateDdl());

        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
                jpaProperties.getHibernate().getDdlAuto());
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager userTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                userEntityManagerFactory().getObject());
        return transactionManager;
    }
}
