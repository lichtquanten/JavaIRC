package Sean;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sean on 5/24/16.
 */
public class Server {
    public static final int PORT = 6001;
    public static int threadID = 0;
    public static ArrayList<ServerThread> servThreadList = new ArrayList<ServerThread>();

    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try{
            serverSocket = new ServerSocket(PORT);
        }
        catch (Exception e){
            System.err.println(e);
            System.out.println("Could not listen on Port " + PORT + ".");
            serverSocket.close();
            System.exit(1);
        }
        System.out.println("Listening on Port " + PORT + ".");
        ServerTransmitter serverTransmitter = new ServerTransmitter();
        Thread sThread = new Thread(serverTransmitter);
        sThread.start();
        while (true){
            clientSocket = null;
            try{
                clientSocket = serverSocket.accept();
                servThreadList.add((new ServerThread(clientSocket, threadID)));
                Thread t = new Thread(servThreadList.get(servThreadList.size()-1));
                t.start();
                System.out.println("Client #" + threadID + " connected.");
                threadID++;
            }
            catch (Exception e) {
                System.err.println(e);
                System.out.println("Could establish connection with client.");
                clientSocket.close();
                System.exit(1);
            }
        }
    }
}