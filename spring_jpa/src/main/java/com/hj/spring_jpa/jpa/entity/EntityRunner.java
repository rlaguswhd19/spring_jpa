package com.hj.spring_jpa.jpa.entity;

import com.hj.spring_jpa.jpa.entity.Account;
import com.hj.spring_jpa.jpa.entity.Study;
import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//@Component
@Transactional // 하나의 트랜잭션으로 실행 = method에 붙일수도 있고 class에 붙여서 모두 적용할 수 있음
public class EntityRunner implements ApplicationRunner {

    @PersistenceContext
    private EntityManager entityManager; // jpa 핵심정인 객체

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Session session = entityManager.unwrap(Session.class); // hibernate 객체

        Account account = new Account();
        account.setUsername("현종");
        account.setPassword("1");

        Study study = Study.builder()
                .name("현종 Class")
                .build();

        account.addStudy(study);
        
        // Transient 상태 : JPA가 모르는 상태


//        entityManager.persist(account); // db에 저장 = 영속화
        session.save(study);
        session.save(account);
//        account.removeStudy(study1);

        // Persistent 상태 : JPA가 관리를 시작하는 상태 (1차 캐시) 이 객체의 변경사항을 모니터링 하고 있다.
        
        // select 쿼리가 발생하지 않음 Persistent Context(Session, EntityManager) 캐시를 주었기 때문에
        Account load = session.load(Account.class, account.getId());
        load.setUsername("erwin0");
        load.setUsername("erwin1");
        load.setUsername("현종");

        // update 쿼리가 나가지 않고 알아서 업데이트 된걸로 insert 함 (Dirty Checking, Wrtie Behind)
        // Dirty Checking : 이 객체의 변경사항을 감시한다.
        // Write Behind : 객체를 적절한 시기에 반영한다.
        System.out.println(load);

        // transaction이 끝나면 Detached
    }
}
