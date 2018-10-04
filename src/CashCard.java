import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * CashCard.java - a class that can check whether a card is expired or not
 * @author Sang To
 * @version 1.0
 */
public class CashCard {
    String cardNum;
    int accNum;
    String bankId;
    LocalDate expDate;

    /**
     * The general format of Dates in this program
     */
    public final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/y");

    /**
     * Create new cash card with its bank id, account number, and expiration date
     * @param bankId This is the bank id
     * @param accNum This is the account number
     * @param day This is the expiration date
     */
    public CashCard(String bankId, int accNum, String day) {
        this.bankId = bankId;
        this.accNum = accNum;
        this.cardNum = bankId + accNum;
        this.expDate = LocalDate.parse(day, formatter);
    }

    private LocalDate getExpDate() {
        return expDate;
    }

    /**
     * Check if the account/card expired
     * @return true if expired
     */
    public boolean isExpired() {
        if (getExpDate().isBefore(LocalDate.now())) {
            return true; //expired
        } else {
            return false;
        }
    }
}
