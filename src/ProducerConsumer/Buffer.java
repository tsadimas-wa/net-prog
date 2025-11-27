/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProducerConsumer;

import java.util.LinkedList;
import java.util.Queue;

class Buffer {
    private final Queue<Integer> list = new LinkedList<>();
    private final int CAPACITY = 5;

    // Μέθοδος για τον Παραγωγό (προσθέτει στο Buffer)
    public void produce(int value) throws InterruptedException {
        // Κλειδώνουμε το ίδιο το αντικείμενο 'this' (το Buffer)
        synchronized (this) {
            // 1. Έλεγχος Ροής: Περίμενε αν το Buffer είναι γεμάτο
            while (list.size() == CAPACITY) {
                System.out.println("BUFFER GEΜΑΤΟ. Παραγωγός περιμένει...");
                wait(); // Απελευθερώνει την κλειδαριά και μπαίνει σε κατάσταση αναμονής
            }

            // 2. Εργασία: Προσθήκη στο Buffer
            list.add(value);
            System.out.println("Παραγωγός παρήγαγε: " + value + ". Μέγεθος: " + list.size());
            
            // 3. Ειδοποίηση: Ειδοποίησε τον Καταναλωτή ότι υπάρχει νέο στοιχείο
            notify(); // Ξυπνάει ένα νήμα που περιμένει (τον Καταναλωτή)
        }
    }

    // Μέθοδος για τον Καταναλωτή (αφαιρεί από το Buffer)
    public int consume() throws InterruptedException {
        int value = 0;
        synchronized (this) {
            // 1. Έλεγχος Ροής: Περίμενε αν το Buffer είναι άδειο
            while (list.isEmpty()) {
                System.out.println("BUFFER ΑΔΕΙΟ. Καταναλωτής περιμένει...");
                wait(); // Απελευθερώνει την κλειδαριά και μπαίνει σε κατάσταση αναμονής
            }

            // 2. Εργασία: Αφαίρεση από το Buffer
            value = list.poll();
            System.out.println("Καταναλωτής κατανάλωσε: " + value + ". Μέγεθος: " + list.size());
            
            // 3. Ειδοποίηση: Ειδοποίησε τον Παραγωγό ότι υπάρχει χώρος
            notify(); // Ξυπνάει ένα νήμα που περιμένει (τον Παραγωγό)
        }
        return value;
    }
}