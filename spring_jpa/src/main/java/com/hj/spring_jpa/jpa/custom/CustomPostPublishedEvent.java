package com.hj.spring_jpa.jpa.custom;

import com.hj.spring_jpa.jpa.cascade.Post;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CustomPostPublishedEvent extends ApplicationEvent {
    private final CustomPost customPost;

    public CustomPostPublishedEvent(Object source) {
        super(source);
        this.customPost = (CustomPost) source;
    }
}
