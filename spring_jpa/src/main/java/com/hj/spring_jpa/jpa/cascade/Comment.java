package com.hj.spring_jpa.jpa.cascade;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
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
    private Integer likeCount = 0;

    private LocalDate created = LocalDate.now();

    @Override
    public String toString() {
        return "Comment{" +
                "comment='" + comment + '\'' +
                ", post=" + post +
                ", likeCount=" + likeCount +
                ", created=" + created +
                '}';
    }
}
