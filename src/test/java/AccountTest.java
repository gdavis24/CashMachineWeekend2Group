import org.junit.Assert;
import org.junit.Test;
import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.BasicAccount;
import rocks.zipcode.atm.bank.PremiumAccount;
import org.junit.Assert;
import org.junit.Test;
import rocks.zipcode.atm.bank.Account;
import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.BasicAccount;
import rocks.zipcode.atm.bank.PremiumAccount;
import static org.junit.Assert.*;
public class AccountTest {
    @Test
    public void withdraw() {
        //given
        AccountData newAccount = new AccountData(1, "Jerry", "jerry@gmail.com", 1000);
        AccountData newAccount2 = new AccountData(2, "Bary", "bary@gmail.com", 15000);
        BasicAccount newAccount3 = new BasicAccount(newAccount);
        PremiumAccount newAccount4 = new PremiumAccount(newAccount2);
        int withdrawAmount = 300;
        //when
        newAccount3.withdraw(withdrawAmount);
        newAccount4.withdraw(withdrawAmount);
        int basicExpected = 700;
        int premiumExpected = 14700;
        int basicActual = newAccount3.getBalance();
        int premiumActual = newAccount4.getBalance();    //then
        Assert.assertEquals(basicExpected, basicActual, 0);
        Assert.assertEquals(premiumExpected, premiumActual, 0);
    }
    @Test
    public void deposit() {
        AccountData newAccount = new AccountData(1, "Jerry", "jerry@gmail.com", 1000);
        AccountData newAccount2 = new AccountData(2, "Bary", "bary@gmail.com", 15000);
        BasicAccount newAccount3 = new BasicAccount(newAccount);
        PremiumAccount newAccount4 = new PremiumAccount(newAccount2);
        int depositAmount = 300;
        //when
        newAccount3.deposit(depositAmount);
        newAccount4.deposit(depositAmount);
        int basicExpected = 1300;
        int premiumExpected = 15300;
        int basicActual = newAccount3.getBalance();
        int premiumActual = newAccount4.getBalance();    //then
        Assert.assertEquals(basicExpected, basicActual, 0);
        Assert.assertEquals(premiumExpected, premiumActual, 0);
    }
    @Test
    public void getBalance()
    {
        AccountData newAccount = new AccountData(1, "Jerry", "jerry@gmail.com", 1000);
        AccountData newAccount2 = new AccountData(2, "Bary", "bary@gmail.com", 15000);
        BasicAccount newAccount3 = new BasicAccount(newAccount);
        PremiumAccount newAccount4 = new PremiumAccount(newAccount2);
        //int depositAmount = 1100;
        //when
        newAccount3.getBalance();
        newAccount4.getBalance();
        int basicExpected = 1000;
        int premiumExpected = 15000;
        int basicActual = newAccount3.getBalance();
        int premiumActual = newAccount4.getBalance();    //then
        Assert.assertEquals(basicExpected, basicActual, 0);
        Assert.assertEquals(premiumExpected, premiumActual, 0);
    }
}