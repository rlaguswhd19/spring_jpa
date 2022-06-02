package com.hj.spring_jpa.jpa.cascade.repository;

import com.hj.spring_jpa.jpa.cascade.Comment;
import com.hj.spring_jpa.jpa.cascade.Post;
import com.hj.spring_jpa.jpa.cascade.impl.CommentOnly;
import com.hj.spring_jpa.jpa.cascade.impl.CommentSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface CommentRepository extends JpaRepository<Comment, Long>, QueryByExampleExecutor<Comment> {

    @EntityGraph(value = "Comment.post")
    Optional<Comment> findById(Long id);

    List<Comment> findByCommentContainsIgnoreCaseAndLikeCountGreaterThanOrderByLikeCountDesc(String keyword, int likeCount);

    Page<Comment> findByCommentContainsIgnoreCase(String keyword, Pageable pageable);

    @Async
    ListenableFuture<List<Comment>> findByCommentContains(String keyword, Pageable pageable);

    Stream<Comment> findByCreatedAfter(LocalDate date, Pageable pageable);

    Page<Comment> findByLikeCountGreaterThanAndPost_IdOrderByCreatedDesc(int likeCount, Long postId, Pageable pageable);
    Page<Comment> findByLikeCountGreaterThanAndPost(int likeCount, Post post, Pageable pageable);

    <T> List<T> findByPost_id(Long Id, Class<T> type);
    List<CommentSummary> findByPost_id(Long Id);
}
