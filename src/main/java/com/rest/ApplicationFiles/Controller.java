package com.rest.ApplicationFiles;

import com.rest.exceptions.BadRequestException;
import com.rest.exceptions.*;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


@RestController
public class Controller {
    static Logger logger;
    static{
        try(FileInputStream inputStream=new FileInputStream("C:\\Users\\Artem\\Desktop\\rest\\src\\main\\java\\com\\rest\\logger\\logger.properties")){
            LogManager.getLogManager().readConfiguration(inputStream);
            logger=Logger.getLogger(Controller.class.getName());
        }
        catch (Exception ignore){
            ignore.printStackTrace();
        }
    }
    @GetMapping("/enter")
    public Note input(@RequestParam(value = "word",required = false) String word, @RequestParam(value = "letter",required = false) String letter){
        try{
            logger.log(Level.INFO,"Start of input. Current parameters:\nword: "+word+"\nletter: "+letter);
            Note wordString=new Note(word,letter);
            wordString.count();
            return wordString;
        }
        catch (BadRequestException badRequestException){
            throw new BadRequestException(badRequestException.getMessage());
        }
        catch(Exception exception){
            logger.log(Level.WARNING,"Caught Exception with request parameters word: "+word+", letter: "+letter);
            throw new InternalException("Internal exception occurred(Exception). Details: "+exception.getMessage());
        }
        catch (Error error){
            logger.log(Level.WARNING,"Caught Error with request parameters word: "+word+", letter: "+letter);
            throw new InternalException("Internal exception occurred(Error). Details: "+error.getMessage());
        }
    }

}