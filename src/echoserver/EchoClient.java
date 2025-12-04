package echoserver;

import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        
        // --- START OF PARAMETER HANDLING ---
        if (args.length != 2) {
            System.err.println("Usage: java EchoClient <host name> <port number>");
            return;
        }

        String hostName = args[0];
        int portNumber;
        
        try {
            portNumber = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Invalid port number: " + args[1] + ". Port must be an integer.");
            return;
        }
        // --- END OF PARAMETER HANDLING ---

        try (
            // Step 1: Create a Socket and connect to the server using provided arguments
            Socket echoSocket = new Socket(hostName, portNumber);
            
            // Step 2: Set up output and input streams for communication
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            
            // Helper to read user input from the console
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Connected to server " + hostName + ":" + portNumber);
            System.out.println("Enter text to send (type 'bye' to disconnect):");
            
            String userInput;
            // Step 3: Read input from console, send to server, and read server's response
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput); // Send the user input
                
                // Read and print the server's echo response
                String serverResponse = in.readLine();
                if (serverResponse == null) {
                    System.out.println("Server disconnected.");
                    break;
                }
                System.out.println("Server response: " + serverResponse); 
                
                if ("bye".equalsIgnoreCase(userInput)) {
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName + ":" + portNumber);
            System.err.println(e.getMessage());
        }
    }
}