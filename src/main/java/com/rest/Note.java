package com.rest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Note {

    public int num;
    private String word;
    public String letter;

    public Note(String word,String letter) {
        this.num = 0;
        this.word = word;
        this.letter=letter;
    }

    void setNum(int num){
        this.num=num;
    }

    public int getNum() {
        return num;
    }

    public String getWord() {
        return word;
    }

    void setWord(String word){this.word=word;}
}
