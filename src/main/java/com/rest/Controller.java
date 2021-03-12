package com.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    Note wordString;
    @GetMapping("/enter")
    public Note greeting(@RequestParam(value = "word", defaultValue = "Word") String word) {
        wordString=new Note(word);
        return wordString;
    }

    @GetMapping("/count")
    public Note count(@RequestParam(value = "letter") char  letter){
        int cntr=0;
        for(int i=0;i<wordString.getContent().length();i++)
        {
            if(letter==wordString.getContent().charAt(i)){
                cntr++;
            }
        }
        wordString.setNum(cntr);
        return wordString;
    }

    @RequestMapping("word")
    public Note print(){

        return wordString;
    }
}