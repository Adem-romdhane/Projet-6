import com.oc.paymybuddy.model.Account;

public class Main {

    public static void main(String[] args) {
        Account account1 = new Account();
        Account account2 = new Account();

        account1.depositMoney(1000);
        account2.depositMoney(4000);
        account1.withdrawMoney(200);
        account2.transferToAnOtherAccount(1000, account1);

        account1.showBalance();
        account2.showBalance();
    }
}
