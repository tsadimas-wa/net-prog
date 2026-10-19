
package bankaccount;

public class BankAccount {

    private int balance = 0;
    
    public void deposit(int amount) {
        int currentBalance = balance;
        
        try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            System.getLogger(BankAccount.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        balance = currentBalance + amount;
        
    }

    public int getBalance() {
        return balance;
    }
    
    
 
    
}
