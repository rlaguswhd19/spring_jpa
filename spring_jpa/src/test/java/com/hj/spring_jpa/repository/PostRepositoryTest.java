package com.hj.spring_jpa.repository;

import com.hj.spring_jpa.jpa.cascade.Post;
import com.hj.spring_jpa.jpa.cascade.QPost;
import com.hj.spring_jpa.jpa.cascade.repository.PostRepository;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @PersistenceContext
    private EntityManager entityManager;

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

    @Test
    public void predicate() {
        Post post = Post.builder()
                .title("erwin")
                .build();

        postRepository.save(post);

        Predicate predicate = QPost.post.title.containsIgnoreCase("erwin");
        Page<Post> pages = postRepository.findAll(predicate, PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id")));

        pages.stream().forEach(System.out::println);

    }

    @Test
    public void createPost() {
        Post post = Post.builder()
                .title("test")
                .build();

        postRepository.save(post);

        List<Post> posts = postRepository.findAll();
    }

    @Test
    public void save() {
         Post post = Post.builder()
//                 .id(1l)
                 .title("erwin")
                 .build();

         // id가 없기 때문에 Transient 새 객체는 persist
        Post save = postRepository.save(post); // insert => persist

        assertThat(entityManager.contains(post)).isTrue();
        assertThat(entityManager.contains(save)).isTrue();
        assertThat(save == post).isTrue();

        Post postUpdate = Post.builder()
                .id(post.getId()) // id가 있기 때문에 merge가 발생함
                .title("test")
                .build();

        Post update = postRepository.save(postUpdate);// update => merge
        // merge의 내부적인 로직은 전달받은 파라미터의 복사본을 생성하고 그 복사본을 가지고 데이터베이스에 영속화를 하고 복사본을 persist로 만들고 return 한다.
        assertThat(entityManager.contains(postUpdate)).isFalse();
        assertThat(entityManager.contains(update)).isTrue();
        assertThat(update == postUpdate).isFalse();


        // persist다 merge다 판단하지 말고 local value 받은 인스턴스를 사용하자. persist
        postUpdate.setTitle("hibernate"); // persist 상태가 아니기 때문에 update쿼리가 test로 날아감
        update.setTitle("hibernate"); // persist 상태이기 때문에 update 쿼리가 hibernate로 날아감 jpa가 감지중이기 때문에

        // 데이터를 가져오려고 하네.. 반영해야겠다. udpate쿼리 날림 그리고 select쿼리로 가져옴
        List<Post> all = postRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void findByTitleStartsWith() {
        Post post = new Post();
        post.setTitle("Spring Data Jpa");

        postRepository.save(post);

        List<Post> spring = postRepository.findByTitleStartsWith("Spring");
        assertThat(spring.size()).isEqualTo(1);
    }

    @Test
    public void Query() {
        Post post = new Post();
        post.setTitle("namedQueryTest");

        postRepository.save(post);

        Post test = postRepository.test("namedQueryTest");
        assertThat(test.getTitle()).isEqualTo("namedQueryTest");

        List<Post> byTitle = postRepository.makeTest();
        assertThat(byTitle.size()).isEqualTo(1);

    }
}
