package com.hj.spring_jpa.jpa.custom;

import com.hj.spring_jpa.jpa.cascade.Post;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class MyRepositoryErwin implements MyRepository<CustomPost>{

    final EntityManager entityManager;

    public MyRepositoryErwin(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<CustomPost> findMyPost() {
        System.out.println("Custom findMyPost");
        return entityManager.createQuery("SELECT cp FROM CustomPost AS cp", CustomPost.class).getResultList();
    }

    @Override
    public void delete(CustomPost entity) {
        System.out.println("Custom delete");
        entityManager.remove(entity);
    }
}
