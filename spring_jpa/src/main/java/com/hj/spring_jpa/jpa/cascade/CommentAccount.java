package com.hj.spring_jpa.jpa.cascade;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentAccount {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;
}
