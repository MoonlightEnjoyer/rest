package com.rest.ApplicationFiles;

import com.rest.exceptions.BadRequestException;
import com.rest.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@RestController
public class Controller{
//        static Logger logger;
//        static {
//            //try(FileInputStream inputStream=new FileInputStream("C:\\Users\\artio\\IdeaProjects\\rest\\src\\main\\java\\com\\rest\\logger\\logger.properties")){
//            try (FileInputStream inputStream = new FileInputStream("C:\\Users\\Artem\\Desktop\\rest\\src\\main\\java\\com\\rest\\logger\\logger.properties")) {
//                LogManager.getLogManager().readConfiguration(inputStream);
//                logger = Logger.getLogger(Controller.class.getName());
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//        }
        ExecutorService executorService= Executors.newFixedThreadPool(4);
        @Autowired
        Cash cash;


        @GetMapping("/enter")
        public void execute(@RequestParam(value = "word", required = false) String word,
                            @RequestParam(value = "letter", required = false) String letter)
        {
            executorService.submit(new responseAction(word,letter,cash));
        }
}