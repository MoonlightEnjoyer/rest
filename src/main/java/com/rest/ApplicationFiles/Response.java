package com.rest.ApplicationFiles;

public class Response {
    private Note note;
    private int quantity;


    public Note getNote() {
        return note;
    }

    public int getQuantity() {
        return quantity;
    }

    public Response(Note note, int quantity) {
        this.note = note;
        this.quantity = quantity;
    }

    public Response(){
        this.quantity=0;
    }

    public Response(Note note){
        this.note=note;
        this.quantity=0;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
