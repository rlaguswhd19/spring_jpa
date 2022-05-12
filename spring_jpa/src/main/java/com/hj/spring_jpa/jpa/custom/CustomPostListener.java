package com.hj.spring_jpa.jpa.custom;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;

public class CustomPostListener {

    @EventListener
    public void onApplicationEvent(CustomPostPublishedEvent event) {
        System.out.println("========================");
        System.out.println(event.getCustomPost().getTitle() + " is published");
        System.out.println("========================");
    }
}
