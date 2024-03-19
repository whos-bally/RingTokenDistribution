import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class Connection extends Thread {
    private Socket outputLine;
    private String hostAddress;
    private int currentPort, nextNodePort;
    //private final int WRITE_LINE = 3;

    public Connection(Socket s, String hAddress, int currPort, int nextPort) {
        this.outputLine = s;
        this.hostAddress = hAddress;
        this.currentPort = currPort;
        this.nextNodePort = nextPort;
    }

    @Override
    public void run() {

        try {
            // create a new data output stream
            System.out.println("Connection to host established: " + new Date());

            System.out.println("******* Passing token to next node ******");
            Socket s = new Socket(hostAddress, nextNodePort);

            Thread.sleep(1000);

            // close socket
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
