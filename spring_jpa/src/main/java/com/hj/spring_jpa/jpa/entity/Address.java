package com.hj.spring_jpa.jpa.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;
    private String city;
    private String state;
    private String zipCode;
}
