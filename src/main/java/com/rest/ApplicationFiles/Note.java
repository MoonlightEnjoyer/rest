package com.rest.ApplicationFiles;


import com.rest.exceptions.BadRequestException;

public class Note {

    private int num;
    private String word;
    private String letter;

    public Note(String word,String letter) {
        this.num = 0;
        this.word = word;
        this.letter=letter;
    }

    public void count(){
        if(word==null || letter==null || word.length()==0 || letter.length()==0)
            throw new BadRequestException("Invalid input. Word and letter fields are mandatory.");
        if(letter.length()!=1)
            throw new BadRequestException("Invalid input. Letter field can contain only one character.");
        for(int i=0;i<word.length();i++)
        {
            if(letter.charAt(0)==word.charAt(i)){
                num++;
            }
        }
    }

    public int getNum() {

        return num;
    }

    public String getWord() {

        return word;
    }

    public String getLetter() {
        return letter;
    }
}
