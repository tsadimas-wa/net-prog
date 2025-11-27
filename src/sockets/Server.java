/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String args[]) throws IOException {
        int port = 9090; // Η θύρα που θα ακούει ο Server

        // 1. Δημιουργία ServerSocket
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("✅ Ο Server τρέχει και περιμένει σύνδεση Client στη θύρα " + port + "...");

            // 2. Αναμονή για σύνδεση Client (μπλοκάρει μέχρι να συνδεθεί)
            Socket clientSocket = serverSocket.accept();
            System.out.println("🔗 Client συνδέθηκε: " + clientSocket.getInetAddress());

            // 3. Setup input/output streams για επικοινωνία
            // BufferedReader για να διαβάζει από τον Client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // PrintWriter για να γράφει στον Client (με autoFlush=true)
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // 4. Επικοινωνία
            String clientMessage = in.readLine();
            System.out.println("🧑 Client λέει: " + clientMessage);

            // Αποστολή απάντησης στον Client
            String response = "🚀 Το μήνυμα σου (" + clientMessage + ") παραλήφθηκε επιτυχώς.";
            out.println(response);
            System.out.println("📩 Απάντηση στάλθηκε στον Client.");

            // 5. Κλείσιμο Socket Client
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("❌ Σφάλμα στον Server: " + e.getMessage());
        }
    }
}