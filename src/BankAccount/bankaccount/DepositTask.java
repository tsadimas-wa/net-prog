
package bankaccount;

public class DepositTask implements Runnable {
    
    private BankAccount account;
    private int iterations;
    private int depositAmount;

    public DepositTask(BankAccount account, int iterations, int depositAmount) {
        this.account = account;
        this.iterations = iterations;
        this.depositAmount = depositAmount;
    }

    @Override
    public void run() {
        for (int i=0; i< iterations; i++) {
            account.deposit(depositAmount);
        }
    }
    
    
    
}
