/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultithreadedServer {
    private static final int PORT = 9090;

    public static void main(String args[]) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("✅ Multithreaded Server τρέχει και ακούει στη θύρα " + PORT + "...");

            // ----------------------------------------------------
            // 2. Κύριος Βρόχος Ακρόασης
            // ----------------------------------------------------
            while (true) {
                // 1. Μπλοκάρουμε και περιμένουμε για νέο Client
                Socket clientSocket = serverSocket.accept();
                
                System.out.println("\n📣 Νέα σύνδεση! Αναθέτουμε σε νήμα. Port: " + clientSocket.getPort());

                // 2. Δημιουργούμε ένα ClientHandler για αυτό το Socket
                ClientHandler clientHandler = new ClientHandler(clientSocket);

                // 3. Δημιουργούμε και ξεκινάμε ένα νέο Νήμα για το ClientHandler
                Thread thread = new Thread(clientHandler);
                thread.start();
                
                // 4. Η κύρια ροή επιστρέφει αμέσως στην αρχή του βρόχου (accept())
            }
        } catch (IOException e) {
            System.err.println("❌ Αδυναμία εκκίνησης Server στη θύρα " + PORT + ". Σφάλμα: " + e.getMessage());
        }
    }
}