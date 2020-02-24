package rocks.zipcode.atm.bank;

/**
 * @author ZipCodeWilmington
 */
public final class AccountData {

    private  int id;
    public   String name;
    private  String email;
    private  int balance;
    private final int monthlyFee = 0 ;
    private int routingNumber;
    private int accountNumber;



    public AccountData(int id, String name, String email, int balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
    }





    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getBalance() {
        return balance;
    }

    //public int getSecurityPin() {return securityPin;}

    // public double getAverageMonthlyBalance() { return AverageMonthlyBalance;}

    public boolean waiver() { return this.waiver();}




    @Override
    public String toString() {
        return "Account id: " + id + '\n' +
                "Name: " + name + '\n' +
                "Email: " + email + '\n' +
                "Balance: " + balance;
    }
}