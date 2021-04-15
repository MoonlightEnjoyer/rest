package com.rest.UnitTests;

import com.rest.ApplicationFiles.Note;
import com.rest.exceptions.BadRequestException;
import org.junit.Test;




import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CountTest {
    @Test
    public void Count_with_correct_parameters(){
        Note testNote=new Note("I hate assembly language","s");
        int result=0;
        result=testNote.count();
        assertEquals("The count method worked properly",2,result);
    }

    @Test
    public void Count_without_letter(){
        Note testNote=new Note("I hate assembly language","");
        Throwable thrown=assertThrows(BadRequestException.class,()->{testNote.count();});
    }

    @Test
    public void Count_with_several_letters(){
        Note testNote=new Note("I hate assembly language","ss");
        Throwable thrown=assertThrows(BadRequestException.class,()->{testNote.count();});
    }

    @Test
    public void Count_without_word(){
        Note testNote=new Note("","s");
        Throwable thrown=assertThrows(BadRequestException.class,()->{testNote.count();});
    }

}
