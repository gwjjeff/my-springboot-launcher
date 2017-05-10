package com.gwjjeff.launchers.mybatis.dao;

import com.gwjjeff.launchers.mybatis.domain.City;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityDao {

	@Autowired
	private SqlSession sqlSession;


	public City selectCityById(long id) {
		return sqlSession.selectOne("selectCityById", id);
	}

	public int selectAutoCommit() {
		return sqlSession.selectOne("selectAutoCommit");
	}

}