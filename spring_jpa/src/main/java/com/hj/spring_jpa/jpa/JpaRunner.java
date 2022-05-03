package com.hj.spring_jpa.jpa;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional // 하나의 트랜잭션으로 실행 = method에 붙일수도 있고 class에 붙여서 모두 적용할 수 있음
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    private EntityManager entityManager; // jpa 핵심정인 객체

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Session session = entityManager.unwrap(Session.class); // hibernate 객체

        Account account1 = new Account();
        account1.setUsername("현종");
        account1.setPassword("1");

        Study study1 = Study.builder()
                .name("현종 Class")
                .build();

        account1.addStudy(study1);


//        entityManager.persist(account); // db에 저장 = 영속화
        session.save(account1);
        session.save(study1);
        account1.remoteStudy(study1);
    }
}
