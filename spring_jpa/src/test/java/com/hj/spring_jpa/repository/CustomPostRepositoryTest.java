package com.hj.spring_jpa.repository;

import com.hj.spring_jpa.jpa.custom.CustomPost;
import com.hj.spring_jpa.jpa.custom.CustomPostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
public class CustomPostRepositoryTest {

    @Autowired
    CustomPostRepository customPostRepository;

    @Test
    @Rollback
    public void crud() {
        List<CustomPost> myPost = customPostRepository.findMyPost();
        CustomPost customPost = new CustomPost();
        customPost.setTitle("Test");
        customPostRepository.save(customPost);

        customPostRepository.findMyPost();

        customPostRepository.delete(customPost);
        customPostRepository.flush();

    }
}
