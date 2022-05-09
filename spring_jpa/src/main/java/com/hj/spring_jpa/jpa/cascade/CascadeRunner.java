package com.hj.spring_jpa.jpa.cascade;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//@Component
@Transactional // 하나의 트랜잭션으로 실행 = method에 붙일수도 있고 class에 붙여서 모두 적용할 수 있음
public class CascadeRunner implements ApplicationRunner {

    @PersistenceContext
    private EntityManager entityManager; // jpa 핵심정인 객체

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Post post = new Post();
//        post.setTitle("Spring JPA 공부하기");
//
//        Comment comment = new Comment();
//        comment.setComment("회사에서 보기");
//
//        post.addComment(comment);
//
//        Comment comment1 = new Comment();
//        comment1.setComment("봐도 되려낭");
//
//        post.addComment(comment1);

        Post load = entityManager.find(Post.class, 1l);
//        entityManager.persist(post);

        System.out.println(load.getTitle());

        load.getComments().forEach(c -> {
            System.out.println(c.getComment());
        });
//        ManyToOne fetch eager 쿼리를 날릴때 join으로 가져오기 '지금'
//        OneToMany fetch lazy 쿼리를 날릴때 join으로 가져오지 않고 foreignkey로 가져와서 '나중에' 필요할때 조회
//        Comment loadComment = entityManager.find(Comment.class, 2l);
//        System.out.println(loadComment.getPost().getTitle());

//        entityManager.remove(load);
    }
}
