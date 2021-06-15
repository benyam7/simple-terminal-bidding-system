import java.util.Scanner;
import data.Database;

public class Main {
    
    private static  Database db;
    public static void main(String[] args){
        System.out.println("Welcome to Bidding System");
        handleFirstChoice();

    }

    private static void handleFirstChoice() {
        // setup db
         setUpDb();
         
        // ask user if there account
        askUserForAccount();
        // if account 
           // go to auction system
              handleAuctionSystem();
           // got to user settings
               handleUserSettings();

        // if no account 
          // create account 
           handleAccountCreation();
          // get back to auction system
            backToMain();

    }

    private static void askUserForAccount() {
        System.out.println("Press 1 to create account \n2 to to login");
        // Using Scanner for Getting Input from User
        Scanner input = new Scanner(System.in);
        int ask = input.nextInt();

        if(ask == 1){
          handleAccountCreation();
        }else if (ask == 2){
           handelLogIn();
        }
    
        // float b = in.nextFloat();
        // System.out.println("You entered float " + b);
       
          // closing scanner
          input.close();
    }

    private static void handelLogIn() {
        System.out.println("Log in proccess");
    }

    private static void backToMain() {
    }

    private static void setUpDb() {
       db  = Database.createDb();
        System.out.println("database set up");
        System.out.println(db.toString());
    }

    private static void handleAccountCreation() {
        // allow user to enter username

        // check if username already exists
        // if not create one
        // take user to auction system to login
        // else tell user account exists and to change email
    }

    private static void handleAuctionSystem() {
        // post auction
        // bid on auction
          // enter amount (also, sanitize the input)
          // update the curent list of auctions
        // see list of current auctions availiable (with auction details)
            // if ot auctions, display none availiable
        
    }

    private static void handleUserSettings() {
        // update user name and password
        // delete user
    }


}
