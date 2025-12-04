package echoserver;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        try (
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String clientIP = clientSocket.getInetAddress().getHostAddress();
            int clientPort = clientSocket.getPort();
            
            System.out.println(threadName + " connected from " + clientIP + ":" + clientPort);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(threadName + " received: " + inputLine);
                
                out.println("Server (" + threadName + ") echoed: " + inputLine);
                
                // When 'bye' is received, break out of the communication loop
                if ("bye".equalsIgnoreCase(inputLine)) {
                    System.out.println(threadName + " received 'bye'. Terminating communication loop.");
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println(threadName + " error: " + e.getMessage());
        } finally {
            try {
                // The thread closes only its assigned socket. The server continues running.
                if (clientSocket != null && !clientSocket.isClosed()) {
                    clientSocket.close();
                }
                System.out.println(threadName + " connection closed.");
            } catch (IOException e) {
                // Ignore
            }
        }
    }
}