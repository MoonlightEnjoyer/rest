package com.rest.ApplicationFiles;

import com.rest.exceptions.BadRequestException;
import com.rest.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


@RestController
public class Controller {
    static Logger logger;
    static{
        try(FileInputStream inputStream=new FileInputStream("C:\\Users\\artio\\IdeaProjects\\rest\\src\\main\\java\\com\\rest\\logger\\logger.properties")){
            LogManager.getLogManager().readConfiguration(inputStream);
            logger=Logger.getLogger(Controller.class.getName());
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
    @Autowired
    Cash cash;
    @GetMapping("/enter")
    public Response input(@RequestParam(value = "word",required = false) String word, @RequestParam(value = "letter",required = false) String letter){
        try{
            logger.log(Level.INFO,"Start of input. Current parameters:\nword: "+word+"\nletter: "+letter);
            Note wordString=new Note(word,letter);
            Response response;
            if(cash.requests.get(word+letter)==null){
                response=new Response(wordString, wordString.count());
                cash.requests.put(word+letter, response.getQuantity());
            }
            else{
                response=new Response(wordString, cash.requests.get(word+letter));
            }
            return response;
        }
        catch (BadRequestException badRequestException){
            throw new BadRequestException(badRequestException.getMessage());
        }
        catch(Exception exception){
            logger.log(Level.SEVERE,"Caught Exception with request parameters word: "+word+", letter: "+letter);
            throw new InternalException("Internal exception occurred(Exception). Details: "+exception.getMessage());
        }
        catch (Error error){
            logger.log(Level.SEVERE,"Caught Error with request parameters word: "+word+", letter: "+letter);
            throw new InternalException("Internal exception occurred(Error). Details: "+error.getMessage());
        }
    }

}