package com.hj.spring_jpa.jpa.cascade;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@NamedEntityGraph(name = "Comment.post",
        attributeNodes = @NamedAttributeNode("post"))
public class Comment {

    @Id
    @GeneratedValue
    private Long Id;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @NotNull
    @Builder.Default
    private Integer likeCount = 0;

    private int up;
    private int down;
    private boolean best;

    @Builder.Default
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
