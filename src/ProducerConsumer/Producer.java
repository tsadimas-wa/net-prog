/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProducerConsumer;

class Producer implements Runnable {
    private final Buffer buffer;
    
    public Producer(Buffer buffer) { this.buffer = buffer; }
    
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) { // Παράγει 10 στοιχεία
            try {
                buffer.produce(i);
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}