package com.hj.spring_jpa.jpa;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate created = LocalDate.now();
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date created = new Date();

    @OneToMany(mappedBy = "owner")
    private Set<Study> studies = new HashSet<>();

    @Transient
    private String no;

    public void addStudy(Study study) {
        this.getStudies().add(study);
        study.setOwner(this);
    }

    public void remoteStudy(Study study) {
        this.getStudies().remove(study);
        study.setOwner(null);
    }
}
