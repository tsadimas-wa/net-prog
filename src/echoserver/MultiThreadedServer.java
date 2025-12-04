package echoserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer {
    public static void main(String[] args) {
        
        // --- START OF PARAMETER HANDLING ---
        int portNumber;
        if (args.length != 1) {
            System.err.println("Usage: java MultiThreadedServer <port number>");
            return;
        }

        try {
            portNumber = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Invalid port number: " + args[0] + ". Port must be an integer.");
            return;
        }
        // --- END OF PARAMETER HANDLING ---
        
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Multi-threaded Server listening on port " + portNumber + "...");

            while (true) {
                System.out.println("Ready to accept a new connection...");
                Socket clientSocket = serverSocket.accept();
                
                // Assign a unique thread name based on the client's port for better tracking
                String threadName = "Client-Thread-" + clientSocket.getPort();
                ClientHandler handler = new ClientHandler(clientSocket);
                
                new Thread(handler, threadName).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            e.printStackTrace();
        }
    }
}