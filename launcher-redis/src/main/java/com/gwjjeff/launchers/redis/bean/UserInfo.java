package com.gwjjeff.launchers.redis.bean;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private String name;
	private int age;
	private String id;
	private Date birthday;
}