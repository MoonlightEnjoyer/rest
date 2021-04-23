package com.rest.ApplicationFiles;

import com.rest.exceptions.BadRequestException;
import com.rest.exceptions.InternalException;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileInputStream;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class responseAction implements Callable {
    public String word;
    public String letter;
    public Cash cash;
    public responseAction(String word, String letter,Cash cash)
    {
        this.word=word;
        this.letter=letter;
        this.cash=cash;
    }
    static Logger logger;
    static {
        //try(FileInputStream inputStream=new FileInputStream("C:\\Users\\artio\\IdeaProjects\\rest\\src\\main\\java\\com\\rest\\logger\\logger.properties")){
        try (FileInputStream inputStream = new FileInputStream("C:\\Users\\Artem\\Desktop\\rest\\src\\main\\java\\com\\rest\\logger\\logger.properties")) {
            LogManager.getLogManager().readConfiguration(inputStream);
            logger = Logger.getLogger(Controller.class.getName());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    @Override
    public Response call() {
            try {
                logger.log(Level.INFO, "Start of input. Current parameters:\nword: " + word + "\nletter: " + letter);
                Note wordString = new Note(word, letter);
                Response response;
                if(cash.requests.get(word + letter) == null) {
                    response = new Response(wordString, wordString.count());
                    logger.log(Level.INFO, "New element");
                    cash.requests.put(word + letter, response.getQuantity());
                } else {
                    response = new Response(wordString, cash.requests.get(word + letter));
                }
                cash.newRequest();
                logger.log(Level.INFO, "Current quantity of requests: " + cash.getRequestsQuantity());
                return response;
            } catch (BadRequestException badRequestException) {
                throw new BadRequestException(badRequestException.getMessage());
            } catch (Exception exception) {
                logger.log(Level.SEVERE, "Caught Exception with request parameters word: " + word + ", letter: " + letter);
                throw new InternalException("Internal exception occurred(Exception). Details: " + exception.getMessage());
            } catch (Error error) {
                logger.log(Level.SEVERE, "Caught Error with request parameters word: " + word + ", letter: " + letter);
                throw new InternalException("Internal exception occurred(Error). Details: " + error.getMessage());
            }
    }
}
