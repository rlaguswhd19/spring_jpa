package com.hj.spring_jpa.jpa.web;

import com.hj.spring_jpa.jpa.cascade.Post;
import com.hj.spring_jpa.jpa.cascade.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable("id") Post post) {
        return post.getTitle();
    }

    @GetMapping("/posts")
    public ResponseEntity<PagedModel<EntityModel<Post>>> getPost(Pageable pageable, PagedResourcesAssembler<Post> pagedResourcesAssembler) {
        Page<Post> page = postRepository.findAll(pageable);
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(page));
    }

    @GetMapping("/posts/save/{title}")
    public EntityModel<Post> savePost(@PathVariable("title") String title) {
        Post post = Post.builder()
                .title(title)
                .build();
        Post newPost = postRepository.save(post);
        return EntityModel.of(newPost);
    }
}
