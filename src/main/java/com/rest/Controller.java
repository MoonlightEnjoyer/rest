package com.rest;

import com.rest.exceptions.ApiException;
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
            logger=Logger.getLogger(Main.class.getName());
        }
        catch (Exception ignore){
            ignore.printStackTrace();
        }
    }
    @GetMapping("/enter")
    public Note greeting(@RequestParam(value = "word",required = false) String word,@RequestParam(value = "letter",required = false) String letter){
        if(word==null || letter==null || word.length()==0 || letter.length()==0)
                throw new BadRequestException("Invalid input. Word and letter fields are mandatory.");
        if(letter.length()!=1)
            throw new BadRequestException("Invalid input. Letter field can contain only one character.");
        try{
            logger.log(Level.INFO,"Start of input.");
            Note wordString=new Note(word,letter);
            int cntr=0;
            for(int i=0;i<wordString.getWord().length();i++)
            {
                if(wordString.letter.charAt(0)==wordString.getWord().charAt(i)){
                    cntr++;
                }
            }
            wordString.setNum(cntr);
            return wordString;
        }
        catch(Exception Exception){
            throw new InternalException("Internal exception occurred(Exception).");
        }
        catch (Error error){
            throw new InternalException("Internal exception occurred(Error).");
        }
    }

//
//    @GetMapping("word")
//    public Note print(){
//
//        return wordString;
//    }
}