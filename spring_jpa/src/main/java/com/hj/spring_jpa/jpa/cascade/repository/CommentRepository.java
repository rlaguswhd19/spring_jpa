package com.hj.spring_jpa.jpa.cascade.repository;

import com.hj.spring_jpa.jpa.cascade.Comment;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

public interface CommentRepository  extends MyRepository<Comment, Long> {

}
