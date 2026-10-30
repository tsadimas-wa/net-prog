
package bankaccount;

public class BankAccount {

    private int balance = 0;
    
    public void deposit(int amount) {
        int currentBalance = balance;
        balance = currentBalance + amount;
        
    }

    public int getBalance() {
        return balance;
    }
    
    
 
    
}
