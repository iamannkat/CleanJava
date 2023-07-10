package com.CleanJava.demo.CleanJava.helpers;

import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationListener;
import org.springframework.session.events.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

@WebListener
public class SessionListener implements ApplicationListener<SessionDestroyedEvent> {

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {
        System.out.println("youre out bitch!!!");
    }
}
