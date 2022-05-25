package com.hj.spring_jpa.repository;

import com.hj.spring_jpa.jpa.cascade.Comment;
import com.hj.spring_jpa.jpa.cascade.Post;
import com.hj.spring_jpa.jpa.cascade.impl.CommentOnly;
import com.hj.spring_jpa.jpa.cascade.impl.CommentSummary;
import com.hj.spring_jpa.jpa.cascade.repository.CommentRepository;
import com.hj.spring_jpa.jpa.cascade.repository.PostRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;


    @BeforeAll
    public void setup() {

        Post post = Post.builder()
                .title("jpa")
                .build();
        Post save = postRepository.save(post);

        Comment comment = Comment.builder()
                .comment("test")
                .post(save)
                .build();

        commentRepository.save(comment);
    }
    @Test
    public void projection() {

        Post post = Post.builder()
                .title("jpa")
                .build();

        Post savePost = postRepository.save(post);

        Comment comment = Comment.builder()
                .comment("comment test")
                .post(savePost)
                .up(10)
                .down(1)
                .build();

        Comment saveComment = commentRepository.save(comment);

//        commentRepository.findByPost_id(savePost.getId(), CommentSummary.class).forEach(c ->{
//            System.out.println(c.getVotes());
//        });

        commentRepository.findByPost_id(savePost.getId()).forEach(c -> System.out.println(c.getVotes()));

//        commentRepository.findByPost_id(savePost.getId(), CommentOnly.class).forEach(c ->{
//            System.out.println(c.getComment());
//        });
//        byPost_id.forEach();
    }

    @Test
    public void getComment() {

        commentRepository.findById(2l);

        System.out.println("=========================");

        commentRepository.findAll();

    }

    @Test
    public void crud() {

        Comment comment = new Comment();
        comment.setComment("test comment");

        commentRepository.save(comment);

        List<Comment> comments = commentRepository.findAll();
        assertThat(comments.size()).isEqualTo(1);
        assertThat(comments).isNotEmpty();

        long count = commentRepository.count();
        assertThat(count).isEqualTo(1);

        Optional<Comment> byId = commentRepository.findById(100L);
        assertThat(byId).isEmpty();
//        byId.orElseThrow(IllegalArgumentException::new);

//        commentRepository.save(null);

    }

    @Test
    public void test() {
        createComment(10, "Spring Jpa test");
        createComment(100, "Hibernate Test");
        createComment(11, "spring testTest");

        List<Comment> comments = commentRepository.findByCommentContainsIgnoreCaseAndLikeCountGreaterThanOrderByLikeCountDesc("test", 10);

//        comments.forEach(System.out::println);

        assertThat(comments.size()).isEqualTo(2);
        assertThat(comments).first().hasFieldOrPropertyWithValue("likeCount", 100);

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "likeCount"));
        Page<Comment> pages = commentRepository.findByCommentContainsIgnoreCase("spring", pageRequest);
        assertThat(pages.getNumberOfElements()).isEqualTo(2);
        assertThat(pages).first().hasFieldOrPropertyWithValue("likeCount", 11);

        Stream<Comment> stream = commentRepository.findByCreatedAfter(LocalDate.now().minusDays(1), PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "likeCount")));
//        List<Comment> testComment = stream.filter(comment ->
//                comment.getComment().startsWith("test")
//        ).collect(Collectors.toList());

        Comment firstComment = stream.findFirst().orElseThrow(NoSuchElementException::new);
        assertThat(firstComment.getLikeCount()).isEqualTo(100);

//        assertThat(stream.collect(Collectors.toList()).size()).isEqualTo(3);
    }

    @Test
    public void future() {

        ListenableFuture<List<Comment>> listenableFuture = commentRepository.findByCommentContains("test", PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "likeCount")));
        listenableFuture.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println(ex.getMessage());
            }

            @Override
            public void onSuccess(List<Comment> result) {
                System.out.println("==================");
                result.forEach(System.out::println);
            }
        });

    }

    public void createComment(int likeCount, String comment) {
        Comment newComment = new Comment();
        newComment.setComment(comment);
        newComment.setLikeCount(likeCount);
        commentRepository.save(newComment);
    }
}
