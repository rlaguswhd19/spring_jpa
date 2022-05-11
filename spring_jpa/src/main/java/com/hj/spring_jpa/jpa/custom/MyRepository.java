package com.hj.spring_jpa.jpa.custom;

import java.util.List;

public interface MyRepository<T> {

    List<CustomPost> findMyPost();

    void delete(T entity);
}
