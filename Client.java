import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private final int READ_LINES = 3;

    public Client(){
        try{
            // start client socket
            System.out.println("******* Initiating token ******");
            Socket s = new Socket("127.0.0.1", 42069);


            // close socket
            System.out.println("******* Closing client socket ******");
            s.close();
        } catch (UnknownHostException e) {
            System.out.println("Unknown Host");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args){
        Client c = new Client();
    }
}
