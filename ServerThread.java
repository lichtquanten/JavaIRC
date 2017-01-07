package Sean;

import java.io.*;
import java.net.Socket;
/**
 * Created by Sean on 6/10/2016.
 */
public class ServerThread implements Runnable {
    public Socket s = null;
    public int threadID = 0;
    BufferedReader inFromClient = null;
    BufferedWriter outToClient = null;
    public boolean messageAvailable = false;
    StringBuilder inputString = new StringBuilder();

    public ServerThread (Socket s, int threadID) throws Exception {
        this.s = s;
        this.threadID = threadID;
        inFromClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
        outToClient = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
    }

    @Override
    public void run() {
        while (true){
            try {
                inputString.append((char)inFromClient.read());
                //System.out.println("!" + inputString + "!");
            }
            catch(IOException e) {
                System.out.println("Could not read from Client #" + threadID);
            }
        }
    }
    public String getInput() {
        //String temp = inputString;
        //inputString = new String("");
        return inputString.toString();
    }
    public void clearInput() {
      inputString.delete(0, inputString.length());
    }
    public void sendToClient (String message) {
        try {
            outToClient.write(message);
            outToClient.flush();
        }
        catch (IOException e) {
            System.out.println("Could not send message to Client # + threadID");
        }
    }
}
