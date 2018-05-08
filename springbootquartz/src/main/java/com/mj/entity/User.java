package com.mj.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "SEX")
    private String sex;

    @Column(name = "AGE")
    private Integer age;


}
