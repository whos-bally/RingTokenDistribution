import java.io.*;
import java.net.BindException;
import java.net.Socket;
import java.util.Date;

public class Connection extends Thread {
    private Socket outputLine;
    private String hostAddress;
    private int currentPort, nextNodePort;

    public Connection(Socket s, String hAddress, int currPort, int nextPort) throws IOException {
        this.outputLine = s;
        this.hostAddress = hAddress;
        this.currentPort = currPort;
        this.nextNodePort = nextPort;
    }

    @Override
    public void run() {

        try {

            System.out.println("Connection to host established: " + new Date());

            System.out.println("******* Passing token to next node ******");

            printToLog();

            Thread.sleep(1000);

            // Pass token to next node
            Socket s = new Socket(hostAddress, nextNodePort);

            // close socket
            outputLine.close();

        }
        catch (BindException e){
            System.out.println("Can't bind socket");
            e.printStackTrace();
            Server.isConnectionDead = true;
            System.exit(1);
        }
        catch (IOException e) {
            e.printStackTrace();
            Server.isConnectionDead = true;
            System.exit(1);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
            e.printStackTrace();
            Server.isConnectionDead = true;
            System.exit(1);
        }
    }

    private synchronized void printToLog() throws IOException {

        try(FileWriter printLog = new FileWriter("printLog.txt", true);
            PrintWriter pw = new PrintWriter(printLog)){

            pw.append("\nHost address: " + hostAddress + " | Current port: " + currentPort
                    + " | Next node: " + nextNodePort + " | Timestamp: " + new Date());

            pw.flush();
        }
        catch (IOException ioe){
            System.out.println("IO Error");
            ioe.getCause();
        }
    }

} // end class
