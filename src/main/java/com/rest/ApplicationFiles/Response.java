package com.rest.ApplicationFiles;

import com.rest.exceptions.BadRequestException;

import javax.persistence.*;

@Entity
@Table(name= "requestresults")
public class Response {
    @Id
    private String word;
    private String letter;
    private int quantity;


    public int count(){
        if(this.word==null || this.letter==null || this.word.length()==0 || this.letter.length()==0)
            throw new BadRequestException("Invalid input. Word and letter fields are mandatory.");
        if(letter.length()!=1)
            throw new BadRequestException("Invalid input. Letter field can contain only one character.");
        for(int i=0;i<word.length();i++)
        {
            if(letter.charAt(0)==word.charAt(i)){
                quantity++;
            }
        }
        return quantity;
    }

    public String getWord() {
        return word;
    }

    public String getLetter() {
        return letter;
    }

    public int getQuantity() {
        return quantity;
    }

    public Response(Note note, int quantity) {
        this.word = note.getWord();
        this.letter=note.getLetter();
        this.quantity = quantity;
    }

    public Response(String word, String letter, int quantity) {
        this.word = word;
        this.letter = letter;
        this.quantity = quantity;
    }

    public Response(){

    }

    public Response(Note note){
        this.word=note.getWord();
        this.letter=note.getLetter();
        this.quantity=0;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class FilterResult extends Response{
    private String filterMessage;

    public String getFilterMessage() {
        return filterMessage;
    }

    public FilterResult(Response response, String filterMessage){
        this.setWord(response.getWord());
        this.setLetter(response.getLetter());
        this.setQuantity(response.getQuantity());
        this.filterMessage=filterMessage;
    }
    public void setResponse(Response response){
        this.setWord(response.getWord());
        this.setLetter(response.getLetter());
        this.setQuantity(response.getQuantity());
    }
    public FilterResult(String filterMessage){
        this.filterMessage=filterMessage;
    }
}
