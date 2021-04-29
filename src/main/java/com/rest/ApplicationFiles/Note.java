package com.rest.ApplicationFiles;


import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rest.exceptions.BadRequestException;
import org.json.JSONObject;

public class Note {

    @JsonProperty("Word")
    private String word;
    @JsonProperty("Letter")
    private String letter;

    public Note(String word,String letter) {
        this.word = word;
        this.letter=letter;
    }

    public Note() {
    }

    public int count(){
        if(word==null || letter==null || word.length()==0 || letter.length()==0)
            throw new BadRequestException("Invalid input. Word and letter fields are mandatory.");
        if(letter.length()!=1)
            throw new BadRequestException("Invalid input. Letter field can contain only one character.");
        int cntr=0;
        for(int i=0;i<word.length();i++)
        {
            if(letter.charAt(0)==word.charAt(i)){
                cntr++;
            }
        }
        return cntr;
    }


    public String getWord() {

        return word;
    }

    public String getLetter() {
        return letter;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }
}
