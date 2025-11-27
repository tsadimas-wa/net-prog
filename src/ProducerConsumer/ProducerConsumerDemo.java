/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProducerConsumer;

/**
 *
 * @author rg
 */
public class ProducerConsumerDemo {
    public static void main(String[] args) throws InterruptedException {
        
        Buffer buffer = new Buffer(); // Κοινόχρηστος πόρος
        
        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread = new Thread(new Consumer(buffer));
        
        producerThread.start();
        consumerThread.start();
        
        producerThread.join();
        consumerThread.join();
        
        System.out.println("\nΌλες οι εργασίες ολοκληρώθηκαν.");
    }
    
}
