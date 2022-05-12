package com.hj.spring_jpa.jpa.custom;

import lombok.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomPost extends AbstractAggregateRoot<CustomPost> {

    @Id @GeneratedValue
    private Long id;

    private String title;

    @Lob
    private String content;

    private LocalDate created = LocalDate.now();

    public CustomPost publish() {
        this.registerEvent(new CustomPostPublishedEvent(this));
        return this;
    }


}
