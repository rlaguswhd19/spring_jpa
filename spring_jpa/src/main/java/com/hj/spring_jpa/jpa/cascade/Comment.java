package com.hj.spring_jpa.jpa.cascade;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue
    private Long Id;

    private String comment;

    @ManyToOne
    private Post post;

    @NotNull
    private int likeCount;

    private LocalDateTime created = LocalDateTime.now();

}
