package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

class Buffer {
    private final Queue<Integer> list = new LinkedList<>();
    private final int capacity;

    public Buffer(int capacity) {
        this.capacity = capacity;
    }

    // Μέθοδος για τον Παραγωγό (προσθέτει στο Buffer)
    public void produce(int value) throws InterruptedException {
        // Κλειδώνουμε το ίδιο το αντικείμενο 'this' (το Buffer).
        // Ισοδύναμο με το να δηλώσουμε τη μέθοδο ως 'public synchronized void produce(...)'
        synchronized (this) {
            // 1. Έλεγχος Ροής: Περίμενε αν το Buffer είναι γεμάτο.
            //    'while' και όχι 'if': όταν ξυπνήσουμε, η συνθήκη πρέπει να ξαναελεγχθεί,
            //    γιατί κάποιο άλλο νήμα μπορεί να πρόλαβε να γεμίσει ξανά το Buffer.
            while (list.size() == capacity) {
                System.out.println("BUFFER ΓΕΜΑΤΟ. " + Thread.currentThread().getName() + " περιμένει...");
                wait(); // Απελευθερώνει την κλειδαριά και μπαίνει σε κατάσταση αναμονής
            }

            // 2. Εργασία: Προσθήκη στο Buffer
            list.add(value);
            System.out.println(Thread.currentThread().getName() + " παρήγαγε: " + value + ". Μέγεθος: " + list.size());

            // 3. Ειδοποίηση: Ξυπνάει ΟΛΑ τα νήματα που περιμένουν σε αυτό το αντικείμενο.
            //    Το καθένα ξαναελέγχει τη συνθήκη του στο while.
            //    (Με notify() θα ξυπνούσε ένα τυχαίο νήμα - με πολλούς παραγωγούς
            //    μπορεί να ξυπνήσει άλλος παραγωγός αντί για καταναλωτή -> deadlock)
            notifyAll();
        }
    }

    // Μέθοδος για τον Καταναλωτή (αφαιρεί από το Buffer)
    public int consume() throws InterruptedException {
        synchronized (this) {
            // 1. Έλεγχος Ροής: Περίμενε αν το Buffer είναι άδειο
            while (list.isEmpty()) {
                System.out.println("BUFFER ΑΔΕΙΟ. " + Thread.currentThread().getName() + " περιμένει...");
                wait(); // Απελευθερώνει την κλειδαριά και μπαίνει σε κατάσταση αναμονής
            }

            // 2. Εργασία: Αφαίρεση από το Buffer
            int value = list.poll();
            System.out.println(Thread.currentThread().getName() + " κατανάλωσε: " + value + ". Μέγεθος: " + list.size());

            // 3. Ειδοποίηση: Ξυπνάει όλα τα νήματα που περιμένουν (υπάρχει πλέον χώρος)
            notifyAll();
            return value;
        }
    }
}
