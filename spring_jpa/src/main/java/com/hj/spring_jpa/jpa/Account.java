package com.hj.spring_jpa.jpa;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

}
