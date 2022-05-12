package com.hj.spring_jpa.repository;

import com.hj.spring_jpa.jpa.custom.CustomPost;
import com.hj.spring_jpa.jpa.custom.CustomPostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
public class CustomPostRepositoryTest {

    @Autowired
    CustomPostRepository customPostRepository;

    @Test
    public void crud() {
        CustomPost customPost = new CustomPost();
        customPost.setTitle("Test");

        assertThat(customPostRepository.contains(customPost)).isFalse();

        customPostRepository.save(customPost);

        assertThat(customPostRepository.contains(customPost)).isTrue();

        customPostRepository.delete(customPost);
        customPostRepository.flush();

    }
}
