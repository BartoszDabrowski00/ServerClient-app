package controller;


import streamData.Message;
import lombok.AllArgsConstructor;

@AllArgsConstructor

public class MessageController{

    public String prepareResponse(Message message){
        System.out.println(message);
        return message.getUsername() + " OK";
    }

}
