package com.gwjjeff.launchers.mybatis.mapper;

import com.gwjjeff.launchers.mybatis.domain.Hotel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HotelMapper {

	@Select("select * from hotel where city = #{city_id}")
	Hotel selectByCityId(@Param("city_id") int id);
}