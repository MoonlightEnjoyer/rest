package com.rest.ApplicationFiles;

import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Cash {
    ConcurrentHashMap<String,Integer> requests=new ConcurrentHashMap<>();
}
