package com.hj.spring_jpa.jpa.custom;

import com.hj.spring_jpa.jpa.basic.BasicRepository;
import com.hj.spring_jpa.jpa.basic.SimpleMyRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CustomPostRepository extends BasicRepository<CustomPost, Long>, QuerydslPredicateExecutor<CustomPost> {

}
