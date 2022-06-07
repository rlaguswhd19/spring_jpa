package com.hj.spring_jpa.jpa.cascade;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommentAccount is a Querydsl query type for CommentAccount
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentAccount extends EntityPathBase<CommentAccount> {

    private static final long serialVersionUID = -868900575L;

    public static final QCommentAccount commentAccount = new QCommentAccount("commentAccount");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final StringPath username = createString("username");

    public QCommentAccount(String variable) {
        super(CommentAccount.class, forVariable(variable));
    }

    public QCommentAccount(Path<? extends CommentAccount> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommentAccount(PathMetadata metadata) {
        super(CommentAccount.class, metadata);
    }

}

