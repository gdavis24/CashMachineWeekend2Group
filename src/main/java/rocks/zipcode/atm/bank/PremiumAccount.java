package rocks.zipcode.atm.bank;

/**
 * @author ZipCodeWilmington
 */
public class PremiumAccount extends Account {

    private static final int OVERDRAFT_LIMIT = 0;

    public PremiumAccount(AccountData accountData) {

        super(accountData);


    }

    @Override
    protected boolean canWithdraw(int amount) {

        return getBalance() + OVERDRAFT_LIMIT >= amount;
    }
}
