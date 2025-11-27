/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RaceCondition;

/**
 *
 * @author rg
 */
    class CounterTask implements Runnable {
    private UnsafeCounter counter;
    private final int iterations = 10000;

    public CounterTask(UnsafeCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {
            counter.increment(); // Καλεί τη ΜΗ ασφαλή μέθοδο
        }
    }
    }
    
