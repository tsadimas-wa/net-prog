/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProducerConsumer;

class Consumer implements Runnable {
    private final Buffer buffer;
    
    public Consumer(Buffer buffer) { this.buffer = buffer; }
    
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) { // Καταναλώνει 10 στοιχεία
            try {
                buffer.consume();
                Thread.sleep(500); // Ο καταναλωτής είναι πιο αργός
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}