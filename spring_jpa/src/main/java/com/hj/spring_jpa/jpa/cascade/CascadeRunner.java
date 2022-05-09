package com.hj.spring_jpa.jpa.cascade;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional // 하나의 트랜잭션으로 실행 = method에 붙일수도 있고 class에 붙여서 모두 적용할 수 있음
public class CascadeRunner implements ApplicationRunner {

    @PersistenceContext
    private EntityManager entityManager; // jpa 핵심정인 객체

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Post post = new Post();
        post.setTitle("Spring JPA 공부하기");

        Comment comment = new Comment();
        comment.setComment("회사에서 보기");

        post.addComment(comment);

        Comment comment1 = new Comment();
        comment1.setComment("봐도 되려낭");

        post.addComment(comment1);

        entityManager.persist(post);

        Post load = entityManager.getReference(Post.class, post.getId());

        entityManager.remove(load);
    }
}
