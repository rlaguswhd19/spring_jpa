package com.hj.spring_jpa.repository;

import com.hj.spring_jpa.jpa.cascade.Post;
import com.hj.spring_jpa.jpa.cascade.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    @Rollback(false) // DataJpaTest 애노테이션이 Test는 어차피 rollback할거니까 insert쿼리를 날리지 않음 그래서 rollback = false 지정
    public void crudRepository() {
        // given
        Post post = new Post();
        post.setTitle("test title");

        assertThat(post.getId()).isNull();

        // when
        Post newPost = postRepository.save(post);

        // then
        assertThat(newPost.getId()).isNotNull();

//        Post byId = postRepository.findById(newPost.getId()).orElseGet(() -> null);
//        System.out.println(byId);

        List<Post> posts = postRepository.findAll();

        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts).contains(newPost);

        Page<Post> pages = postRepository.findAll(PageRequest.of(0, 10));
        assertThat(pages.getTotalElements()).isEqualTo(1); // 전체 페이지 갯수?
        assertThat(pages.getNumber()).isEqualTo(0); // 페이지 넘버
        assertThat(pages.getSize()).isEqualTo(10); // 요청한 사이즈
        assertThat(pages.getNumberOfElements()).isEqualTo(1); // 페이지 넘버에 들어있는 post 갯수

        pages = postRepository.findByTitleContains("test", PageRequest.of(0, 10));
        assertThat(pages.getTotalElements()).isEqualTo(1); // 전체 페이지 갯수?
        assertThat(pages.getNumber()).isEqualTo(0); // 페이지 넘버
        assertThat(pages.getSize()).isEqualTo(10); // 요청한 사이즈
        assertThat(pages.getNumberOfElements()).isEqualTo(1); // 페이지 넘버에 들어있는 post 갯수

        Long count = postRepository.countByTitleContains("test");
        assertThat(count).isEqualTo(1);
    }
}
