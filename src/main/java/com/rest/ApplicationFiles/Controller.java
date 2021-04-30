package com.rest.ApplicationFiles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.exceptions.BadRequestException;
import com.rest.exceptions.*;
import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Stream;


@RestController
public class Controller {
    static Logger logger;

    static {
        try(FileInputStream inputStream=new FileInputStream("C:\\Users\\artio\\IdeaProjects\\rest\\src\\main\\java\\com\\rest\\logger\\logger.properties")){
        //try (FileInputStream inputStream = new FileInputStream("C:\\Users\\Artem\\Desktop\\rest\\src\\main\\java\\com\\rest\\logger\\logger.properties")) {
            LogManager.getLogManager().readConfiguration(inputStream);
            logger = Logger.getLogger(Controller.class.getName());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Autowired
    Cash cash;
    @Autowired
    RequestCounter requestCounter;

//    @GetMapping("/enter")
//    @Async("threadPoolTaskExecutor")
//    //public Response input(@RequestParam(value = "word",required = false) String word, @RequestParam(value = "letter",required = false) String letter){
//    //public CompletableFuture<Response> input(@RequestParam(value = "word",required = false) String word, @RequestParam(value = "letter",required = false) String letter){
//    public CompletableFuture<ArrayList<Response>> inputByGet(@RequestParam(value = "note", required = false) List<String> requestParams) {
//        try{
//            //logger.log(Level.INFO,"Start of input. Current parameters:\nword: "+word+"\nletter: "+letter);
//            ArrayList<Response> responseList=new ArrayList<>();
//            ObjectMapper objectMapper=new ObjectMapper();
//            requestParams.stream().forEach((element)->{try{responseList.add(new Response(objectMapper.readValue(element,Note.class)));}catch(IOException exception){throw new InternalException("Internal exception occurred(Exception). Details: "+exception.getMessage());}});
//            responseList.stream().forEach((element)->{
//                if(cash.requests.get(element.getNote().getWord()+element.getNote().getLetter())==null){
//                    element.setQuantity(element.getNote().count());
//                    cash.requests.put(element.getNote().getWord()+element.getNote().getLetter(),element.getQuantity());
//                }
//                else{
//                    element.setQuantity(cash.requests.get(element.getNote().getWord()+element.getNote().getLetter()));
//                }
//            });
////            if(cash.requests.get(word+letter)==null){
////                response=new Response(wordString, wordString.count());
////                logger.log(Level.INFO,"New element");
////                cash.requests.put(word+letter, response.getQuantity());
////            }
////            else{
////                response=new Response(wordString, cash.requests.get(word+letter));
////            }
////            requestCounter.counter.incrementAndGet();
////            logger.log(Level.INFO,"Quantity of requests: "+requestCounter.counter);
//            //return response;
//            return CompletableFuture.completedFuture(responseList);
//        }
//        catch (BadRequestException badRequestException){
//            throw new BadRequestException(badRequestException.getMessage());
//        }
//        catch(Exception exception){
//           // logger.log(Level.SEVERE,"Caught Exception with request parameters word: "+word+", letter: "+letter);
//            throw new InternalException("Internal exception occurred(Exception). Details: "+exception.getMessage());
//        }
//    }

    @PostMapping("/enter")
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<ArrayList<Response>> inputByPost(
            @RequestParam(value = "note", required = false) List<String> requestParams) {
        try{
            ArrayList<Response> responseList=new ArrayList<>();
            ObjectMapper objectMapper=new ObjectMapper();
            requestParams.stream().forEach((element)->{
                try{
                    Response response=new Response(objectMapper.readValue(element,Note.class));
                    if(response.getNote()==null || response.getNote().getLetter()==null || response.getNote().getWord()==null)
                        throw new BadRequestException("Invalid input. Word and letter fields are mandatory.");
                    else
                        responseList.add(response);
                }
                catch(IOException exception){
                    throw new InternalException("Internal exception occurred(Exception). Details: "+exception.getMessage());
                }
                catch(BadRequestException badRequestException)
                {
                    throw new BadRequestException(badRequestException.getMessage());
                }
            });
            responseList.stream().forEach((element)->{
                if(cash.requests.get(element.getNote().getWord()+element.getNote().getLetter())==null) {
                    try {
                        element.setQuantity(element.getNote().count());
                    } catch (BadRequestException badRequestException) {
                        throw new BadRequestException(badRequestException.getMessage());
                    } catch (Exception exception) {
                        logger.log(Level.SEVERE, "Caught Exception with following request parameters: " + requestParams);
                        throw new InternalException("Internal exception occurred(Exception). Details: " + exception.getMessage());
                    }
                    cash.requests.put(element.getNote().getWord() + element.getNote().getLetter(), element.getQuantity());
                    logger.log(Level.INFO, "Added new element.");
                }
                else{
                    element.setQuantity(cash.requests.get(element.getNote().getWord()+element.getNote().getLetter()));
                }
            });
            logger.log(Level.INFO,"Quantity of requests: "+requestCounter.counter.incrementAndGet());
            return CompletableFuture.completedFuture(responseList);
        }
        catch (BadRequestException badRequestException){
            throw new BadRequestException(badRequestException.getMessage());
        }
        catch(Exception exception){
            logger.log(Level.SEVERE,"Caught Exception with following request parameters: "+requestParams);
            throw new InternalException("Internal exception occurred(Exception). Details: "+exception.getMessage());
        }
    }

    @GetMapping("/counter")
    public AtomicLong returnCounter()
    {
        return requestCounter.counter;
    }

}