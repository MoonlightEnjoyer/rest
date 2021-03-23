package com.rest;

import com.rest.exceptions.ApiException;
import com.rest.exceptions.BadRequestException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Validated
public class Controller {

    @GetMapping("/enter")
    public Note greeting(@RequestParam(value = "word",required = false) String word,@RequestParam(value = "letter",required = false) String letter){
        //throw new BadRequestException("Fuck you.");
//        try{
//            if(letter.length()!=1)
//                throw new BadRequestException("You are fucked");
//        }
        if(word==null || letter==null || word.length()==0 || letter.length()==0)
                throw new BadRequestException("Invalid input. Word and letter fields are mandatory.");
        if(letter.length()!=1)
            throw new BadRequestException("Invalid input. Letter field can contain only one character.");
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

//
//    @GetMapping("word")
//    public Note print(){
//
//        return wordString;
//    }
}