package com.hj.spring_jpa.jpa.cascade;

import com.hj.spring_jpa.jpa.entity.Account;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    private LocalDate created = LocalDate.now();


    @ManyToOne
    @CreatedBy
    private CommentAccount createBy;

    @ManyToOne
    @LastModifiedBy
    private CommentAccount updateBy;

    @LastModifiedDate
    private LocalDate updated;

    @PrePersist
    public void prePersist() {
        System.out.println("JPA event call back");
    }

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
