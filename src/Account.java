/**
 * Account.java - a class that contains an account with its account number, balance, password and associated Cash card
 * @author Sang To
 * @version 1.0
 */
public class Account {

    int accNum;
    int balance;
    CashCard cashCard;
    String password;

    /**
     * Create an account with input account number, balance, associated Cash card, and password
     * @param accNum This is the account number
     * @param balance This is the preloaded balance
     * @param cashCard This is the associated cash card
     * @param password This is the account's password
     */
    public Account(int accNum, int balance, CashCard cashCard, String password) {
        this.accNum = accNum;
        this.balance = balance;
        this.cashCard = cashCard;
        this.password = password;
    }

    private int getAccNum() {
        return accNum;
    }

    /**
     * Get account's balance
     * @return a number of balance of the account
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Set the balance of the account
     * @param balance This is the balance of the account
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Get the account's password
     * @return a string of password of the account
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get the associated cash card
     * @return the associated cash card of the account
     */
    public CashCard getCashCard() {
        return cashCard;
    }

    /**
     * {@inheritDoc}
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return ", account: #" + getAccNum() + ", exp: " + CashCard.formatter.format(getCashCard().expDate) + ", pass: " + getPassword();
    }
}
