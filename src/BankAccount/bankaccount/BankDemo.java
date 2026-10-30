/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaccount;

/**
 *
 * @author rg
 */
public class BankDemo {
    public static void main(String[] args) {
        BankAccount sharedAccount = new BankAccount();
        int iterations = 10000;
        int depositAmount = 1;
        
        Thread wife = new Thread(new DepositTask(sharedAccount, iterations, depositAmount),"wife");
        Thread husband = new Thread(new DepositTask(sharedAccount, iterations, depositAmount),"husband");
        
        wife.start();
        husband.start();
        
        try {
            wife.join();
             husband.join();
        } catch (InterruptedException ex) {
            System.getLogger(BankDemo.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
      
        // Υπολογίζουμε τι θα ΕΠΡΕΠΕ να έχει ο λογαριασμός
        int expectedBalance = 2 * iterations * depositAmount; 

        System.out.println("--- Αποτελέσματα Καταθέσεων ---");
        System.out.println("Αναμενόμενο Υπόλοιπο: " + expectedBalance + "€");
        System.out.println("Πραγματικό Υπόλοιπο : " + sharedAccount.getBalance() + "€");
        System.out.println("Χρήματα που 'χάθηκαν' λόγω Race Condition: " + (expectedBalance - sharedAccount.getBalance()) + "€");
    }
}
