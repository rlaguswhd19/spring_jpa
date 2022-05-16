package com.hj.spring_jpa.repository;

import com.hj.spring_jpa.jpa.custom.CustomPost;
import com.hj.spring_jpa.jpa.custom.CustomPostPublishedEvent;
import com.hj.spring_jpa.jpa.custom.CustomPostRepository;
import com.hj.spring_jpa.jpa.custom.QCustomPost;
import com.querydsl.core.types.Predicate;
import org.assertj.core.api.PredicateAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(CustomPostRepositoryTestConfig.class)
public class CustomPostRepositoryTest {

    @Autowired
    CustomPostRepository customPostRepository;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void crud() {
        CustomPost customPost = new CustomPost();
        customPost.setTitle("Test");

        assertThat(customPostRepository.contains(customPost)).isFalse();

        customPostRepository.save(customPost.publish());

        assertThat(customPostRepository.contains(customPost)).isTrue();

        customPostRepository.delete(customPost);
        customPostRepository.flush();

    }

    @Test
    public void event() {
        CustomPost customPost = new CustomPost();
        customPost.setTitle("event test");
        CustomPostPublishedEvent customPostPublishedEvent = new CustomPostPublishedEvent(customPost);

        applicationContext.publishEvent(customPostPublishedEvent);
    }

    @Test
    public void QueryDsl() {
        CustomPost customPost = new CustomPost();
        customPost.setTitle("hibernate");

        customPostRepository.save(customPost);

        Predicate predicate = QCustomPost.customPost.title.containsIgnoreCase("hibernate");
        Optional<CustomPost> one = customPostRepository.findOne(predicate);
        System.out.println(one.get().getTitle());
        assertThat(one).isNotEmpty();

    }

}
