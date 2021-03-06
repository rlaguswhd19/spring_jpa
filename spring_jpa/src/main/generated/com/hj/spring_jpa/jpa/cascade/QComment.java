package com.hj.spring_jpa.jpa.cascade;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = -182022292L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComment comment1 = new QComment("comment1");

    public final BooleanPath best = createBoolean("best");

    public final StringPath comment = createString("comment");

    public final QCommentAccount createBy;

    public final DatePath<java.time.LocalDate> created = createDate("created", java.time.LocalDate.class);

    public final NumberPath<Integer> down = createNumber("down", Integer.class);

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final NumberPath<Integer> likeCount = createNumber("likeCount", Integer.class);

    public final QPost post;

    public final NumberPath<Integer> up = createNumber("up", Integer.class);

    public final QCommentAccount updateBy;

    public final DatePath<java.time.LocalDate> updated = createDate("updated", java.time.LocalDate.class);

    public QComment(String variable) {
        this(Comment.class, forVariable(variable), INITS);
    }

    public QComment(Path<? extends Comment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComment(PathMetadata metadata, PathInits inits) {
        this(Comment.class, metadata, inits);
    }

    public QComment(Class<? extends Comment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createBy = inits.isInitialized("createBy") ? new QCommentAccount(forProperty("createBy")) : null;
        this.post = inits.isInitialized("post") ? new QPost(forProperty("post")) : null;
        this.updateBy = inits.isInitialized("updateBy") ? new QCommentAccount(forProperty("updateBy")) : null;
    }

}

