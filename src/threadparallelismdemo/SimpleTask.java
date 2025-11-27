/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadparallelismdemo;

/**
 *
 * @author rg
 */
public class SimpleTask implements Runnable {
    private String threadName;

    public SimpleTask(String name) {
        this.threadName = name;
        System.out.println(threadName + " created.");
    }

    @Override
    public void run() {
        System.out.println("--- " + threadName + " STARTED! ---");

        // Execute 5 steps
        for (int i = 1; i <= 5; i++) {
            System.out.println(threadName + ": Vima " + i);
            try {
                // Wait for a short time to show interleaving
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                System.out.println(threadName + " interrupted.");
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println("--- " + threadName + " FINISHED! ---");
    }
    
}
