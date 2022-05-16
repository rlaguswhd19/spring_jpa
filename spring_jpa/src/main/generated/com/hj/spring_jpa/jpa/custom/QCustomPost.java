package com.hj.spring_jpa.jpa.custom;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCustomPost is a Querydsl query type for CustomPost
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomPost extends EntityPathBase<CustomPost> {

    private static final long serialVersionUID = 1294462741L;

    public static final QCustomPost customPost = new QCustomPost("customPost");

    public final StringPath content = createString("content");

    public final DatePath<java.time.LocalDate> created = createDate("created", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public QCustomPost(String variable) {
        super(CustomPost.class, forVariable(variable));
    }

    public QCustomPost(Path<? extends CustomPost> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCustomPost(PathMetadata metadata) {
        super(CustomPost.class, metadata);
    }

}

