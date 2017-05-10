package com.gwjjeff.launchers.mybatis;

import com.gwjjeff.base.BaseDemoLauncher;
import com.gwjjeff.launchers.mybatis.dao.CityDao;
import com.gwjjeff.launchers.mybatis.mapper.HotelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component("mybatis_launcher")
public class Launcher extends BaseDemoLauncher {
    private static final Logger logger = LoggerFactory.getLogger(Launcher.class);

    @Autowired
    private CityDao cityDao;

    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public void doRun(ApplicationArguments args) throws Exception {
        logger.info("=============  MYBATIS LAUNCHER  ===============");
        logger.info(cityDao.selectCityById(1).toString());
        logger.info(hotelMapper.selectByCityId(1).toString());
        logger.info("@@autocommit = {}", cityDao.selectAutoCommit());
    }
}