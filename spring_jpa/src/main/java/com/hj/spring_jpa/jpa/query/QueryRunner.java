package com.hj.spring_jpa.jpa.query;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class QueryRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {

//        jpql
//        Query query = entityManager.createQuery("SELECT p FROM Post AS p", Post.class);
//        List<Post> posts = query.getResultList();
//
//        posts.forEach(System.out::println);

//        time safe 문자열 사용 x
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Post> query = builder.createQuery(Post.class);
//        Root<Post> from = query.from(Post.class);
//
//        query.select(from);
//
//        List<Post> posts = entityManager.createQuery(query).getResultList();
//        posts.forEach(System.out::println);



    }
}
