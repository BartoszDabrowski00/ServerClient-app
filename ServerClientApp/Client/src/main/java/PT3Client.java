import client.Client;

public class PT3Client {
    public static void main(String[] args){
        Thread clientThread = new Thread(new Client());
        clientThread.start();
    }
}
