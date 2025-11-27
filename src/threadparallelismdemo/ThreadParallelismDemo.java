/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package threadparallelismdemo;

/**
 *
 * @author rg
 */
public class ThreadParallelismDemo {

    public static void main(String[] args) {
        
        // 2. Create the tasks (Runnable objects)
        SimpleTask taskA = new SimpleTask("Thread-1");
        SimpleTask taskB = new SimpleTask("Thread-2");
        
        // 3. Create the Thread objects and assign the tasks
        Thread tA = new Thread(taskA);
        Thread tB = new Thread(taskB);
        
        System.out.println("\nStarting execution...");
        
        // 4. Start the execution (calls run() asynchronously)
        tA.start();
        tB.start();
        
        // 5. Optionally, wait for them to finish (using join)
        try {
            tA.join();
            tB.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        
        System.out.println("\nMain finished");
    }
    
}
