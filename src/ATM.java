/**
 * ATM.java - a class that contains all necessary functions of an ATM, contacts its bank to retrieve user's information
 * @author Sang To
 * @version 1.0
 */
public class ATM {
    private String name;
    private Bank bank;
    private static final int MAXAMOUT = 50;
    private String num;
    private String bankId = null;
    private int accNum;

    /**
     * Create a new ATM with the given name and its bank
     * @param name This is the ATM's name
     * @param bank This is the associated bank
     */
    public ATM(String name, Bank bank) {
        this.name = name;
        this.bank = bank;
    }

    /**
     * Store the input variables for later usage
     * @param num This is the card number input passed in as a String
     */
    public void load(String num) {
        this.num = num;
        this.bankId = num.substring(0,1);
        this.accNum = Integer.valueOf(num.substring(1, num.length()));
    }

    /**
     * Check if a card number is valid/exists.
     * @param num This is the card number input passed in as a String
     * @return true if the card exists in this bank
     */
    public boolean checkCardNum(String num) {
        this.num = num;
        String bankId = num.substring(0,1);
        int accNum = Integer.valueOf(num.substring(1, num.length()));

        if (bankId.equals(bank.getId()) && bank.getAccount(accNum) !=null) {
            return true;
        }
        return false;
    }

    /**
     * Check if this card has expired or not
     * @return true if the card expired
     */
    public boolean checkExpired() {
        int accNum = Integer.valueOf(num.substring(1, num.length()));
        Account account = bank.getAccount(accNum);
        if (account.getCashCard().isExpired()) {
            return true;
        }
        return false;
    }

    /**
     * Check if the input password matches with the one in the system associate with this account
     * @param pass This is the input password
     * @return true if the input password matches with the one in the system
     */
    public boolean checkPassword(String pass) {
        int accNum = Integer.valueOf(num.substring(1, num.length()));
        Account account = bank.getAccount(accNum);
        if (account.getPassword().equals(pass)) {
            return true;
        }
        return false;
    }

    /**
     * Check if the amount requested in the range allowed per transaction
     * @param amount This is the amount requested
     * @return true if the requested amount is smaller or equal to the max amount per transaction
     */
    public boolean perTransaction(int amount) {
        if (amount > MAXAMOUT) {
            return false; // excess amount allowed per transaction
        }
        return true;
    }

    /**
     * Check if the amount requested in the range of account's balance
     * @param amount This is the amount requested
     * @return true if the requested amount is smaller or equal to the available balance
     */
    public boolean checkBalance(int amount) {
        int accNum = Integer.valueOf(num.substring(1, num.length()));
        Account account = bank.getAccount(accNum);
        if (amount > account.getBalance()) {
            return false; // excess the current balance of the account
        }
        account.setBalance(account.getBalance()-amount);
        return true;
    }

    /**
     * Get the balance of user's account
     * @return the available balance that the user has in this account
     */
    public int getBalance() {
        int accNum = Integer.valueOf(num.substring(1, num.length()));
        return bank.getAccount(accNum).getBalance();
    }

    /**
     * {@inheritDoc}
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return name + ": The maximum amount of cash a card can withdraw per transaction: $" + MAXAMOUT;
    }
}
