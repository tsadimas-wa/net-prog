/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientHandler implements Runnable {
    private Socket clientSocket;

    // Ο constructor δέχεται το Socket του Client
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        // Ο κώδικας εδώ εκτελείται στο νέο νήμα
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        ) {
            String clientMessage;
            System.out.println("🔗 Νέο νήμα χειρίζεται Client από: " + clientSocket.getInetAddress());

            // Διαβάζουμε συνεχώς μηνύματα από τον Client μέχρι να στείλει 'bye'
            while ((clientMessage = in.readLine()) != null) {
                System.out.println("🧑 Client " + clientSocket.getPort() + " λέει: " + clientMessage);
                
                if ("bye".equalsIgnoreCase(clientMessage)) {
                    // Στέλνουμε μήνυμα αποχαιρετισμού και τερματίζουμε τον βρόχο
                    out.println("👋 Bye! Τερματίζουμε την σύνδεση.");
                    break; 
                }
                
                // Απάντηση στον Client
                String response = "🚀 Server: Έλαβα το '" + clientMessage + "'.";
                out.println(response);
            }
        } catch (IOException e) {
            System.err.println("❌ Σφάλμα επικοινωνίας με Client: " + e.getMessage());
        } finally {
            try {
                // Κλείνουμε το Socket και απελευθερώνουμε τους πόρους
                clientSocket.close();
                System.out.println("✖️ Σύνδεση με Client (" + clientSocket.getPort() + ") τερματίστηκε.");
            } catch (IOException e) {
                System.err.println("Σφάλμα κατά το κλείσιμο του Socket: " + e.getMessage());
            }
        }
    }
}