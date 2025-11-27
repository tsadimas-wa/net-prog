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
import java.net.UnknownHostException;

public class Client {
    public static void main(String args[]) throws IOException {
        String serverHost = "localhost"; // Διεύθυνση του Server (για την ίδια μηχανή)
        int port = 9090; // Η θύρα του Server

        // 1. Δημιουργία Socket για σύνδεση στον Server
        try (Socket socket = new Socket(serverHost, port)) {
            System.out.println("✅ Συνδέθηκε στον Server (" + serverHost + ":" + port + ")");

            // 2. Setup input/output streams για επικοινωνία
            // PrintWriter για να στέλνει δεδομένα στον Server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // BufferedReader για να διαβάζει δεδομένα από τον Server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 3. Επικοινωνία
            String messageToSend = "Γεια σου Server! Είμαι ο Client.";
            out.println(messageToSend); // Στέλνει το μήνυμα
            System.out.println("📩 Στάλθηκε στον Server: " + messageToSend);

            // Λήψη απάντησης από τον Server
            String serverResponse = in.readLine();
            System.out.println("🤖 Server απαντά: " + serverResponse);

            // 4. Το socket κλείνει αυτόματα λόγω του try-with-resources
        } catch (UnknownHostException e) {
            System.err.println("❌ Άγνωστος host: " + serverHost);
        } catch (IOException e) {
            System.err.println("❌ Δεν ήταν δυνατή η σύνδεση στον Server: " + e.getMessage());
        }
    }
}