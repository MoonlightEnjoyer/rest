package com.rest.ApplicationFiles;

import java.util.concurrent.atomic.AtomicLong;

public class RequestCounter {
    final AtomicLong counter = new AtomicLong();
    public RequestCounter(){
        counter.set(0);
    }
}
