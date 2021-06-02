import controller.MessageController;
import controller.IncomingConnection;

public class PT3 {
    public static void main(String[] args){
        MessageController messageController = new MessageController();
        IncomingConnection incomingConnection = new IncomingConnection(messageController);
        Thread serverThread = new Thread(incomingConnection);
        serverThread.start();
        try {
            Thread.sleep(1000000000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Time's up, closing server");
        serverThread.interrupt();
    }
}
