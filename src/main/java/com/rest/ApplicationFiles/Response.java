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
}
