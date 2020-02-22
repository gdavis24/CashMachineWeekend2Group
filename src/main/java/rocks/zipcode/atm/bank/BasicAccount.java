package rocks.zipcode.atm.bank;

/**
 * @author ZipCodeWilmington
 */
public class BasicAccount extends Account {

    private static final int OVERDRAFT_LIMIT = 500;

    public BasicAccount(AccountData accountData) {

        super(accountData);


    }

    @Override
    protected boolean canWithdraw(int amount) {

        return getBalance() + OVERDRAFT_LIMIT >= amount;
    }
}
