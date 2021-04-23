package com.rest.ApplicationFiles;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class Cash {
    ConcurrentHashMap<String,Integer> requests=new ConcurrentHashMap<>();
    private AtomicLong requestsQuantity=new AtomicLong();
    public Cash()
    {
        requestsQuantity.set(0);
    }
    public void newRequest()
    {
        requestsQuantity.incrementAndGet();
    }

    public AtomicLong getRequestsQuantity() {
        return requestsQuantity;
    }
}
