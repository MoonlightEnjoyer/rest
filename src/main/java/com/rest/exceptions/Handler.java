package com.rest.exceptions;

import com.rest.Main;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileInputStream;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@ControllerAdvice
public class Handler {
    static Logger logger;
    static{
        try(FileInputStream inputStream=new FileInputStream("C:\\Users\\Artem\\Desktop\\rest\\src\\main\\java\\com\\rest\\logger\\logger.properties")){
            LogManager.getLogManager().readConfiguration(inputStream);
            logger=Logger.getLogger(Handler.class.getName());
        }
        catch (Exception ignore){
            ignore.printStackTrace();
        }
    }
    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleRequestException(BadRequestException e){
        ApiException apiException=new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
        logger.log(Level.WARNING,HttpStatus.BAD_REQUEST+" exception occurred.");
        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {InternalException.class})
    public ResponseEntity<Object> handleInternalException(InternalException internalException){
        ApiException apiException=new ApiException(internalException.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,ZonedDateTime.now(ZoneId.of("Z")));
        logger.log(Level.WARNING,HttpStatus.INTERNAL_SERVER_ERROR+" exception occurred.");
        return  new ResponseEntity<>(apiException,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
