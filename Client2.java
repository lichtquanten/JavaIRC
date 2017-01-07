package Sean;

import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.DataOutputStream;
//import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.util.Scanner;
/**
 * Created by Sean on 5/24/16.
 */
public class Client2 {
    public static void main(String[] args) throws Exception {
        Socket clientSocket = null;

        try {
            clientSocket = new Socket("localhost",6001);
        }
        catch (IOException e){
            System.err.println(e);
            System.out.println("FROMCLIENT - Could not establish connection with server.");
            clientSocket.close();
            System.exit(1);
        }
        System.out.println("FROMCLIENT - Connection established with server");
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        Scanner in = new Scanner(System.in);
        String input;
        System.out.println("Enter a message:");
        while(!(input = in.nextLine()).equalsIgnoreCase("Exit")){
            outToServer.writeBytes(input);
            System.out.println("Enter a message:");
        }
        clientSocket.close();
    }
}
