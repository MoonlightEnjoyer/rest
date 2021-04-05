package com.rest.ApplicationFiles;


import com.rest.exceptions.BadRequestException;

public class Note {

    private String word;
    private String letter;

    public Note(String word,String letter) {
        //this.num = 0;
        this.word = word;
        this.letter=letter;
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
}
