import java.util.Scanner;

/**
 * ATMSystem.java - the main class that has the methods to ask for user's input and run accordingly as requested
 * @author Sang To
 * @version 1.0
 */
public class ATMSystem {
    static Bank bankA = new Bank("A");
    static Bank bankB = new Bank("B");
    static Scanner sc = new Scanner(System.in);
    static ATM atmMachine;

    /**
     * The main method creates the banks, its ATM system, and list of user's information, take user's input
     * @param args Unused
     */
    public static void main(String[] args) {

        bankA.createAccount(11, 100, "9/23/2018" , "myfirst");
        bankA.createAccount(22, 70, "10/30/2018" , "mysecond");
        bankB.createAccount(111, 50, "8/23/2019" , "mythird");
        bankB.createAccount(122, 100, "10/30/2017" , "myfourth");
        bankB.createAccount(133, 120, "12/30/2018" , "myfifth");

        System.out.println("BankOfA");
        System.out.println(bankA);
        System.out.println("BankOfB");
        System.out.println(bankB);
        System.out.println("States of 4 ATMs");

        for (ATM atm : bankA.getAtmList()) {
            System.out.println(atm);
        }

        for (ATM atm : bankB.getAtmList()) {
            System.out.println(atm);
        }
        System.out.println();
        takingInput();
    }

    private static void takingInput() {
        while (true) {
            //input ATM from user
            System.out.println("Enter your choice of ATM (A1, A2, B1, B2):");
            String atm = sc.nextLine();
            String atmId = String.valueOf(atm.charAt(0)); // check if input other banks not in list
            //input card from user
            System.out.println("Enter your card number");
            String cardNum = sc.nextLine();
            String bankId = String.valueOf(cardNum.charAt(0));
            String order = String.valueOf(cardNum.charAt(1));
            if (!bankId.equals(atmId)) {
                System.out.println("This card is not supported by this ATM.");
                continue;
            }

            //Check to determine which ATM from which bank was requested by the user and load the card
            loadATM(cardNum, bankId, order);

            if (!atmMachine.checkCardNum(cardNum)) {
                System.out.println("The account does not exist.");
                continue;
            }

            // check the boolean logic here
            if (atmMachine.checkExpired()) {
                System.out.println("This card is expired and returned to you.");
                continue;
            }
            System.out.println("The card is accepted. Please enter your password:");
            String pass = sc.nextLine();
            while (!atmMachine.checkPassword(pass)) {
                System.out.println("This is wrong password. Enter your password:");
                pass = sc.nextLine();
            }

            System.out.print("Authorization is accepted. ");
            while (true)
            {
                System.out.println("Please enter the amount to withdraw or quit: ");
                String input = sc.nextLine().trim();

                if (input.equals("quit")) { break;}
                int amountInput = Integer.parseInt(input);
                if (!atmMachine.perTransaction(amountInput))
                    System.out.print("This amount exceeds the maximum amount you can withdraw per transaction. ");
                else if (!atmMachine.checkBalance(amountInput))
                    System.out.print("The amount exceeds the current balance.");
                else System.out.print("$" + String.valueOf(amountInput) + " is withdrawn from  your account. " +
                            "The remaining balance of this account is " + String.valueOf(atmMachine.getBalance()) + ". ");
            }
            sc.close();
            break;
        }
    }

    private static void loadATM(String cardNum, String bankId, String order) {

        if (bankId.equals(bankA.getId())) {
            switch (order) {
                case "1":
                    atmMachine = bankA.getAtmList().get(0);
                    atmMachine.load(cardNum);
                    break;
                default:
                    atmMachine = bankA.getAtmList().get(1);
                    atmMachine.load(cardNum);
                    break;
            }
        } else {
            switch (order) {
                case "1": atmMachine = bankB.getAtmList().get(0); atmMachine.load(cardNum); break;
                default: atmMachine = bankB.getAtmList().get(1); atmMachine.load(cardNum); break;
            }
        }
    }
}
