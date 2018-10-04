import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bank.java - a class that contains its ATMs, it can retrieve bankId, create accounts, and print list of accounts
 * @author Sang To
 * @version 1.0
 */
public class Bank {
    //An account is associated with one and only one cash card. A cash card comes with a card
    // number from which bank_id and the associated account number can be identified.
    // A cash card also comes with its expiration date.
    private String id;
    private Map<Integer, Account> accountMap = new HashMap<>();
    private List<ATM> atmList = new ArrayList<>();

    /**
     * Get the atmList of each bank
     * @return a new ArrayList of the atmList
     */
    public List<ATM> getAtmList() {
        return new ArrayList<>(atmList);
    }

    /**
     * Get account according to the card number input by user
     * @param accNum This is the account number
     * @return the specified account from the Map
     */
    public Account getAccount(int accNum) {
        return accountMap.get(accNum);
    }

    /**
     * Create a new bank with its id
     * @param id This is the bank's id
     */
    public Bank(String id) {
        this.id = id;
        final ATM atm1 = new ATM(getId() + "1", this);
        atmList.add(atm1);
        final ATM atm2 = new ATM(getId() + "2", this);
        atmList.add(atm2);
    }

    /**
     * Get bank's id
     * @return the string of bank's id
     */
    public String getId() {
        return id;
    }

    /**
     * Create a new account and add to the bank's map
     * @param accNum This is the input account number
     * @param balance This is the preloaded balance of the account
     * @param exp This is the expiration date of the account/card
     * @param password This is the password used to access bank account
     */
    public void createAccount(int accNum, int balance, String exp, String password) {
        CashCard card = new CashCard(id, accNum, exp);
        Account account = new Account(accNum, balance, card, password);
        accountMap.put(accNum, account);
    }

    /**
     * {@inheritDoc}
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        String s = "";
        //for (Integer i : accountMap.keySet())
        for (Map.Entry<Integer, Account> accountEntry : accountMap.entrySet()) {
            Account a = accountEntry.getValue();
            s += "BankId: " + getId() + a.toString() + "\n";
        }
        return s;
    }
}
