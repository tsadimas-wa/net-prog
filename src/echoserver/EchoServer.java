import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        
        // --- START OF PARAMETER HANDLING ---
        int portNumber;
        if (args.length != 1) {
            // If no argument is provided, print usage and exit
            System.err.println("Usage: java EchoServer <port number>");
            return; 
        }

        try {
            portNumber = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Invalid port number: " + args[0] + ". Port must be an integer.");
            return;
        }
        // --- END OF PARAMETER HANDLING ---

        try (
            ServerSocket serverSocket = new ServerSocket(portNumber);
        ) {
            System.out.println("Single-Client Server started on port " + portNumber + " (Sequential mode)");

            // Outer loop: Keep the server running forever to accept new clients
            while (true) {
                Socket clientSocket = null;
                try {
                    System.out.println("\nReady to accept a new connection...");
                    clientSocket = serverSocket.accept(); 
                    
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);                   
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                
                    String clientIP = clientSocket.getInetAddress().getHostAddress();
                    int clientPort = clientSocket.getPort();
                    
                    System.out.println("Client connected. Details: IP=" + clientIP + ", Port=" + clientPort);
                    System.out.println("Starting communication...");

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("Received: " + inputLine);
                        out.println("Server echoed: " + inputLine);
                        if ("bye".equalsIgnoreCase(inputLine)) {
                            break;
                        }
                    }
                    
                } catch (IOException e) {
                    System.err.println("Error handling client connection: " + e.getMessage());
                } finally {
                    if (clientSocket != null) {
                        clientSocket.close();
                        System.out.println("Connection closed. Waiting for next client...");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            e.printStackTrace();
        }
    }
}