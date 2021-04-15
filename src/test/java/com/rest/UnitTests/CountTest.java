package com.rest.UnitTests;

import com.rest.ApplicationFiles.Controller;
import com.rest.ApplicationFiles.Note;
import com.rest.exceptions.InternalException;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.Level;



import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CountTest {
    static Logger logger;
    static{
        try(FileInputStream inputStream=new FileInputStream("C:\\Users\\artio\\IdeaProjects\\rest\\src\\main\\java\\com\\rest\\logger\\logger.properties")){
            LogManager.getLogManager().readConfiguration(inputStream);
            logger=Logger.getLogger(Controller.class.getName());
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
    @Test
    public void Count_with_correct_parameters(){
        //System.out.println("\nCount with correct parameters:");
        logger.log(Level.INFO,"\nCount with correct parameters:");
        Note testNote=new Note("I hate assembly language","s");
        int result=0;
        Throwable thrown=assertThrows(Exception.class,()->{result= testNote.count();});
//        try{
//        result=testNote.count();
//        }
//        catch (Exception exception){
//            throw new InternalException(exception.getMessage());
//        }
        assertEquals("The count method worked properly",2,result);
    }

    @Test
    public void Count_without_letter(){
        //System.out.println("\nCount without letter:");
        logger.log(Level.INFO,"\nCount without letter:");
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
        //System.out.println("\nCount with several letters:");
        logger.log(Level.INFO,"\nCount with several letters:");
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
        //System.out.println("\nCount without word:");
        logger.log(Level.INFO,"\nCount without word:");
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
