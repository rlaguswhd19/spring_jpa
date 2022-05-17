package com.hj.spring_jpa.jpa.cascade.repository;

import com.hj.spring_jpa.jpa.cascade.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PostRepository extends JpaRepository<Post, Long>, QuerydslPredicateExecutor<Post> {

    Page<Post> findByTitleContains(String title, Pageable pageable);
    long countByTitleContains(String title);
}
