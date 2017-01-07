package Sean;

import java.io.IOException;
import java.util.ArrayList;
/**
 * Created by Sean on 6/13/2016.
 */
public class ServerTransmitter implements Runnable {
    @Override
    public void run() {
        String message;
        while (true){
            try {
                for (ServerThread sT : Server.servThreadList) {
                    String a = sT.getInput();
                    if (a.contains("+!")) {
                        //System.out.println("\n@"+a);
                        sT.clearInput();
                        sendAll(sT.threadID + ": " + a);
                    }
                }

                //throttle the loop a bit
                Thread.sleep(100);
            }
            catch (Exception e) {
                System.out.println("Couldn't read from client.");
            }
        }
    }
    public static void sendAll(String message) throws IOException {
        System.out.print("BROADCASTING: " + message);
        for (ServerThread sT : Server.servThreadList) {
            sT.sendToClient(message);
        }
    }
}
