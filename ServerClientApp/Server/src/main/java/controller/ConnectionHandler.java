package controller;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import streamData.Message;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConnectionHandler implements Runnable{
    private final Socket socket;
    private final MessageController controller;

    @Override
    public void run(){
        try(ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))){
            try {
                Message message = (Message)input.readObject();
                String receiveTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
                message.setReceiveDate(receiveTime);
                String response = controller.prepareResponse(message);
                output.write(response);
            }
            catch(ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        catch(IOException ignore){

        }
    }

}
