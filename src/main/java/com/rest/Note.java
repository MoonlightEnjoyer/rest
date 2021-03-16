package com.rest;

public class Note {

    public int num;
    private String content;
    public char letter;

    public Note(String content,char letter) {
        this.num = 0;
        this.content = content;
        this.letter=letter;
    }

    void setNum(int num){
        this.num=num;
    }

    public int getNum() {
        return num;
    }

    public String getContent() {
        return content;
    }
}
