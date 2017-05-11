package com.gwjjeff.launchers.jpaOracle.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "jpa_demo_user1")
public class User {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
 
    private String name;
 
    @Column(unique = true, nullable = false)
    private String email;
 
    private int age;
}
