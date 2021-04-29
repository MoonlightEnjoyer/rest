package com.rest.ApplicationFiles;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class RequestCounter {
    final AtomicLong counter = new AtomicLong();
    public RequestCounter(){
        counter.set(0);
    }
}
