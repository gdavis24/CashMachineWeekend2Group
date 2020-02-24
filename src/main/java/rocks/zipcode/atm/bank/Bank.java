package rocks.zipcode.atm.bank;

import rocks.zipcode.atm.ActionResult;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    public Bank() {
        accounts.put(1, new BasicAccount(new AccountData(
                1, "James", "example1@gmail.com", 500
        )));

        accounts.put(2, new PremiumAccount(new AccountData(
                2, "Bill", "example2@gmail.com", 200)));

    }

    public ActionResult<AccountData> createAccount(int id, String name, String email, int balance, String accountType) {
        Boolean check = checkIds(id);
        if (check) {
            if (accountType.equals("Basic")) {
                accounts.put(id, new BasicAccount(new AccountData(
                        id, name, email, 0
                )));
            }
            if (accountType.equals("Premium")) {
                accounts.put(id, new PremiumAccount(new AccountData(
                        id, name, email, 0
                )));
            }
            Account newAccount = accounts.get(id);
            return ActionResult.success(newAccount.getAccountData());
        }

        return ActionResult.fail("taken");
    }



    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("No account with id: " + id );
        }
    }

    public ActionResult<AccountData> deposit(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        account.deposit(amount);

        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        boolean ok = account.withdraw(amount);

        if (ok) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());
        }
    }
    //----------------
    public Boolean checkIds(Integer acctId) {
        Boolean x = true;
        for(Integer i : accounts.keySet()){
            if(i.equals(acctId)) {
                x=false;
            }
        }
        System.out.println(x);
        return x;
    }

}

