package com.hj.spring_jpa.jpa;

import com.hj.spring_jpa.jpa.cascade.repository.PostRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    private final PostRepository postRepository;

    public JpaRunner(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
//        postRepository.findAll().forEach(System.out::println);
    }
}
