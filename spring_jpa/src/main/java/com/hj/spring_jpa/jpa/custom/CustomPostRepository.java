package com.hj.spring_jpa.jpa.custom;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomPostRepository extends JpaRepository<CustomPost, Long>, MyRepository<CustomPost> {

}
