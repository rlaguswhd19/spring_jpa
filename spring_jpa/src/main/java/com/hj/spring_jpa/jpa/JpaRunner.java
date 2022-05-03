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
        Account account = Account.builder()
                .username("김현종1")
                .password("jpa")
                .build();

//        entityManager.persist(account); // db에 저장 = 영속화

        Session session = entityManager.unwrap(Session.class); // hibernate 객체
        session.save(account);
    }
}
