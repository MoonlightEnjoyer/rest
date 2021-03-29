package com.rest.UnitTests;

import com.rest.ApplicationFiles.Note;
import com.rest.exceptions.InternalException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CountTest {

    @Test
    public void Count_with_correct_parameters(){
        System.out.println("\nCount with correct parameters:");
        Note testNote=new Note("I hate assembly language","s");
        int result=0;
        try{
        result=testNote.count();
        }
        catch (Exception exception){
            throw new InternalException(exception.getMessage());
        }
        assertEquals("The count method didn't work properly",2,result);
    }

    @Test
    public void Count_without_letter(){
        System.out.println("\nCount without letter:");
        Note testNote=new Note("I hate assembly language","");
        int result=0;
        try{
            result=testNote.count();
        }
        catch (Exception exception){
            System.out.println("Exception caught. "+exception.getMessage());
        }
        assertEquals("The count method didn't work properly",0,result);
    }

    @Test
    public void Count_with_several_letters(){
        System.out.println("\nCount with several letters:");
        Note testNote=new Note("I hate assembly language","ss");
        int result=0;
        try{
            result=testNote.count();
        }
        catch (Exception exception){
            System.out.println("Exception caught. "+exception.getMessage());
        }
        assertEquals("The count method didn't work properly",0,result);
    }

    @Test
    public void Count_without_word(){
        System.out.println("\nCount without word:");
        Note testNote=new Note("","s");
        int result=0;
        try{
            result=testNote.count();
        }
        catch (Exception exception){
            System.out.println("Exception caught. "+exception.getMessage());
        }
        assertEquals("The count method didn't work properly",0,result);
    }

}
