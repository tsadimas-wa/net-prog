/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RaceCondition;

/**
 *
 * @author rg
 */
public class UnsafeCounter {
    private int count = 0;

    // ΜΗ συγχρονισμένη μέθοδος
    public synchronized void increment() {
        // Η λειτουργία count++ δεν είναι ατομική και μπορεί να διακοπεί
        count++; 
    }

    public int getCount() {
        return count;
    }
    
}
