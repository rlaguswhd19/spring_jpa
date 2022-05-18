package com.hj.spring_jpa.controller;

import com.hj.spring_jpa.jpa.cascade.Post;
import com.hj.spring_jpa.jpa.cascade.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void getPost() throws Exception {
        Post post = Post.builder()
                .title("jpa")
                .build();

        postRepository.save(post);

        mockMvc.perform(get("/posts/" + post.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("jpa"))
                .andDo(print())
        ;
    }

    @Test
    public void getPosts() throws Exception {
        createPost();

        mockMvc.perform(get("/posts")
                        .param("page","0")
                        .param("size", "10")
                        .param("sort", "title,desc")
//                        .param("sort", "title")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("page.size").exists())
                .andExpect(jsonPath("page.size").value("10"))
                .andDo(print())
        ;
    }

    public void createPost() {
        int postCount = 100;

        while(postCount > 0) {
            Post post = Post.builder()
                    .title("test" + postCount)
                    .build();

            postRepository.save(post);

            postCount--;
        }
    }
}
