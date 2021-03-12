package com.rest;

public class Note {

    public int num;
    private String content;

    public Note(String content) {
        this.num = 0;
        this.content = content;
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
