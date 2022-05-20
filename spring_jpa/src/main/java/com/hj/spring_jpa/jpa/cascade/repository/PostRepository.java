package com.hj.spring_jpa.jpa.cascade.repository;

import com.hj.spring_jpa.jpa.cascade.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, QuerydslPredicateExecutor<Post> {

    Page<Post> findByTitleContains(String title, Pageable pageable);
    long countByTitleContains(String title);
    List<Post> findByTitleStartsWith(String start);
    @Query("SELECT p FROM Post p WHERE p.title = ?1")
    Post test(String title);
    @Query(nativeQuery = true, value = "select * from post limit 1")
    List<Post> makeTest();
    @Query("SELECT p FROM Post p WHERE p.title = ?1")
    List<Post> sortTest(String title, Sort sort);
    @Query("SELECT p FROM #{#entityName} AS p WHERE p.title = :title")
    List<Post> paramTest(@Param("title") String keyword, Sort sort);

}
