/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RaceCondition;

/**
 *
 * @author rg
 */
public class RaceConditionDemo {
    public static void main(String[] args) throws InterruptedException {
        // Δημιουργία ΕΝΟΣ κοινόχρηστου αντικειμένου
        UnsafeCounter counter = new UnsafeCounter(); 

        // Δημιουργία ΕΝΟΣ Runnable που χρησιμοποιεί το κοινόχρηστο αντικείμενο
        Runnable task = new CounterTask(counter);

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();

        // Χρησιμοποιούμε join() για να περιμένουμε να τελειώσουν και τα δύο νήματα
        t1.join();
        t2.join();

        // Τελικό αποτέλεσμα: Σχεδόν πάντα μικρότερο από 20000
        System.out.println("----------------------------------------");
        System.out.println("Αναμενόμενο αποτέλεσμα: 20000");
        System.out.println("ΠΡΑΓΜΑΤΙΚΟ αποτέλεσμα (ΧΩΡΙΣ ΣΥΓΧΡΟΝΙΣΜΟ): " + counter.getCount());
        System.out.println("----------------------------------------");
    }
}
