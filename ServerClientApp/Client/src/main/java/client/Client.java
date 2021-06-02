package client;


import streamData.Message;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Client implements Runnable{
    private String username;
    public static int PORT = 1234;
    @Override
    public void run(){
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("What's your username?");
        username = scanner.nextLine();
        while(!quit){
            String command = scanner.nextLine();
            if ("quit".equals(command)) {
                quit = !quit;
            } else {
                sendMessage(command);
            }
        }
    }

    private void sendMessage(String content){
        try(Socket client = new Socket("localhost",PORT)){
            try(BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream())){
                String currentTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
                Message message = Message.builder()
                        .username(username)
                        .content(content)
                        .sendDate(currentTime)
                        .build();
                output.writeObject(message);
                System.out.println(input.readLine());
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
