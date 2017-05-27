package com.gwjjeff.launchers.customSpec.domain;

import lombok.Data;

import javax.persistence.*;

@Entity //1
@Data
@NamedQuery(name = "Person.withNameAndAddressNamedQuery",
query = "select p from Person p where p.name=?1 and address=?2")
public class Person {
    @Id //2
	// oracle
	// @GeneratedValue //3
	// mysql
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private Integer age;
	
	private String address;
	
	public Person() {
		super();
	}
	public Person(Long id, String name, Integer age, String address) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
	}

}
