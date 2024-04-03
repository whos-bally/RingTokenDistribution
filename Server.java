import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private Connection conn;

    private int currentPort, nextNodePort;
    private String hostAddress;
    public static boolean isConnectionDead = false;

    /** Server is provided 3 arguments to know which node it is and where the next node is
     *  in order to pass the token
     * @param currentPort Port number of the host
     * @param hostAddr Host IP address
     * @param nextNode Port number of the next host
     */

    public Server(int currentPort, String hostAddr, int nextNode){

        // save arguments entered to console
        this.currentPort = currentPort;
        this.hostAddress = hostAddr;
        this.nextNodePort = nextNode;

        // create new socket to listen to
        try{
            serverSocket = new ServerSocket(currentPort);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // listen for connections
        print("****** Server is listening ******");


        try {
            do {
                // Accept client socket
                socket = serverSocket.accept();
                print("Host address: " + hostAddress + "\nCurrent port: " + currentPort
                        + "\nNext node: " + nextNode);
                // Create a thread for client service
                conn = new Connection(socket, hostAddress, currentPort, nextNode);
                conn.start();
            }
            while (!isConnectionDead);
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void print(String s){
        System.out.println(s);
    }

    public static void main(String[] args){

        if (args.length != 3) {
            System.out.println("Usage: <current port> <host address> <next node port>");
            System.exit(1);
        }
        else{
            Server s = new Server(Integer.parseInt(args[0]), args[1], Integer.parseInt(args[2]));
        }



    }

}
