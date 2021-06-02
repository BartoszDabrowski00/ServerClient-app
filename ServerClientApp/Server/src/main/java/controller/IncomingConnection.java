package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class IncomingConnection implements Runnable{
    private ServerSocket server;
    private final MessageController controller;
    public static int PORT = 1234;

    public IncomingConnection(MessageController controllers){
        this.controller = controllers;
        try{
            server = new ServerSocket(PORT);
            server.setSoTimeout(1000);
            System.out.println("Server started at port: "+PORT);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        while(!Thread.interrupted()){
            try{
                Socket socket = server.accept();
                if(socket!=null) {
                    System.out.println("New connection");
                    new Thread(new ConnectionHandler(socket, controller)).start();
                }
            } catch(IOException e){

            }
        }
    }
}
