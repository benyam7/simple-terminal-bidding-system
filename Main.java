import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import data.Database;
import data.models.*;

public class Main {
    
    private static  Database db;
    public static void main(String[] args){
        System.out.println("Welcome to Bidding System");
         // setup db
        setUpDb();
        handleFirstChoice();

    }

    private static void handleFirstChoice() {
     
        // // ask user if there account
        askUserForAccount();
        // // if login 
        //    // go to auction system
        //       handleAuctionSystem();
        //    // got to user settings
        //        handleUserSettings();

        // // if no account 
        //   // create account 
        //    handleAccountCreation();
        //   // get back to auction system
        //     backToMain();

    }


    private static void askUserForAccount() {
        System.out.println("Press 1 to create account \n2 to to login");
        // Using Console for Getting Input from User
       
        String ask = System.console().readLine();

        if(ask.equals("1")){
          handleAccountCreation();
        }else if (ask.equals("2")){
           handelLogIn();
        }
    
        // float b = in.nextFloat();
        // System.out.println("You entered float " + b);
       
       
    }

    private static void handelLogIn() {
        System.out.println("Log in proccess");

        System.out.println("Enter your username\nUsername: ");

        String usernameInput = System.console().readLine();
        System.out.println("Enter password");
        String passwordInput = System.console().readLine();
       
        // check if username  exists
        // get all users and loop
        ArrayList<User> users =  (ArrayList<User>) db.get("users");

       boolean isAutehnticated = false;
       for( User user : users){
           System.out.println(user.getUsername());
           System.out.println(user.getPassword());
          if(usernameInput.equals(user.getUsername()) && passwordInput.equals(user.getPassword())){
              isAutehnticated = true;
              //set current user
              db.put("currentUserName", user.getUsername());

                System.out.println("1 to Auction system\n2 to user settings");
                String choice = System.console().readLine();
                if(choice.equals("1")){ 
                    handleAuctionSystem();

                 } else if(choice.equals("2")){
                     handleUserSettings();
                 } else {
                     System.out.println("unsupported input ");
                     handelLogIn();
                 }
              
          }
        }

        if(isAutehnticated ==false){
        // user not found
        System.out.println("Wrong credentials");
        handleFirstChoice();
        }
    }


    private static void backToMain() {
    }

    private static void setUpDb() {
       db  = Database.createDb();
       db.put("users", new ArrayList<User>());
       ArrayList<User> users = (ArrayList) db.get("users");
       users.add(new User(
         "username",
     " password",
        0, 0, 0
       ));

       db.put("auctions", new ArrayList<Auction>());
       db.put("currentUserName", "" );
        System.out.println("database set up");

        System.out.println(db.toString());
    }

    private static void handleAccountCreation() {
        // allow user to enter username
        System.out.println("Enter your username\nUsename: ");

        String usernameInput = System.console().readLine();
        
       
        // check if username already exists
        // get all users and loop
        ArrayList<User> users =  (ArrayList<User>) db.get("users");

        // System.out.println(users.toString());
        for( User user : users){
          if(usernameInput.equals(user.getUsername())){
              System.out.println("user already exists");
              handleFirstChoice();
              return;
          }
        }
        System.out.println("Enter password");
        String passwordInput = System.console().readLine();

        // if not create one based on user input
        users.add(new User(usernameInput.toString() , passwordInput.toString(), 0, 0, 0));

       // take user to auction system to login
       handleFirstChoice();
       

    }

    private static void handleAuctionSystem() {
        System.out.println("login success");

        System.out.println("1 to post auction");
        System.out.println("2 to see list of auctions");
        System.out.println("3 to bid on auction");

        
        String input = System.console().readLine();

        switch(input){
           case "1": { hanldelPostAuction();
            break;
        }
        case "2": {
            handelListOfAuctions();
            break;
        }
        case "3": {
            handelBidOnAuction();
            break;
        }
        default : {
            System.out.println("unsupported input");
            handleAuctionSystem();
        }
        }
        // post auction
        // bid on auction
          // enter amount (also, sanitize the input)
          // update the curent list of auctions
        // see list of current auctions availiable (with auction details)
            // if ot auctions, display none availiable
        
    }

    private static void handelBidOnAuction() {
        // ask on what item the user is biding on (id)
        // ask bid amount
        // save who's biding (the current user)

        System.out.println("Enter id of item you're bidding ");
        String itemIdInput = System.console().readLine();

        System.out.println("How much to do you want to bid on ?");
        String bidAmoutInput = System.console().readLine();

        // get list of auctions first

        ArrayList<Auction> auctions = (ArrayList<Auction>) db.get("auctions");

        boolean isAuctionIdCorrect = false;
        // find the auction by id 
        for(Auction auction: auctions){
            if(auction.getId().equals(itemIdInput)){
                auction.addBidOnAuction(new Bid((String)db.get("currentUserName"), auction.getId(), Double.parseDouble( bidAmoutInput)));
                isAuctionIdCorrect = true;
                // list the auctions , like trying to verify
                handelListOfAuctions();
            }
        }
         // validate if the acution doesnt' exist anymore, redo the thing
         if(isAuctionIdCorrect == false){
            System.out.println("Auction id doesn't match");
            handleAuctionSystem();
         }
        // 

    }

    private static void handelListOfAuctions() {
        System.out.println("Current list of auctions");
        // get the auctions form db
        ArrayList<Auction> auctions = (ArrayList<Auction>) db.get("auctions");
        
        if(auctions.isEmpty()){
            System.out.println();
            System.out.println("===========NO AUCTIONS AVAILIABLE YET===================");
            handleAuctionSystem();
            return;
        }
        // show  details of each auction
        for(Auction auction : auctions){
            System.out.println();
            System.out.println("==============================");
            System.out.println("Auction ID: " + auction.getId());
            System.out.println("Posted by: " + auction.getOwner());
            System.out.println("current bids on item: " + auction.getBids());
            System.out.println("Initial price: " + auction.getIntialPrice());
            System.out.println("Auction ends at: " + auction.getEndTime());
            System.out.println("Item name: " + auction.getItem().getName());
            System.out.println("Item Description: " + auction.getItem().getDescription());
            System.out.println("==============================");
            System.out.println();
        }

        handleAuctionSystem();

    }

    private static void hanldelPostAuction() {
        
     
        // ask intial price of the item
        // ask name 
        // ask description of the item

        System.out.println("Enter name of the item ");
        String nameInput = System.console().readLine();

        System.out.println("Enter description of item");
        String descriptionInput = System.console().readLine();

        System.out.println("Enter initial price of the item");
        Double initialPriceInput = Double.parseDouble(System.console().readLine());

        System.out.println("How long should the auction stay (enter in days)");
        int lengthOfAuctionInput = Integer.parseInt(System.console().readLine());

        // getEndTime
        Date endTime = getEndTimeFromLength(lengthOfAuctionInput);

        // put the details on auctions 
        ArrayList<Auction> auctions = (ArrayList<Auction>) db.get("auctions");

        // store auction to db
        auctions.add(new Auction(new Item(
            nameInput,
            descriptionInput
        ), (String) db.get("currentUserName"), new ArrayList<Bid>(), new Date(), endTime, initialPriceInput));
        

        System.out.println("You're auction has been posted");

        // take them to list of auctions
        handelListOfAuctions();

    }

    private static Date getEndTimeFromLength(int days) {
        var endDate =  new Date().getDay() + days;
        return new Date(2021,6,endDate);
    }

    private static void handleUserSettings() {
        System.out.println("=========USER SETTINGS===========");
        System.out.println("1 to update user name and password\n2 to delete user from system");
        
        String input = System.console().readLine();

        if(input.equals("1")){
            handelUserUpdate();
        }else if (input.equals("2")){
            handelUserDelete();
        } else {
            System.out.println("unsupported input");
            handleUserSettings();
        }
        // update user name and password
        // delete user
    }

    private static void handelUserDelete() {
        System.out.println("Are you sure you want to delete current user?\n1 to contiue deleting\n2 to stop deleting");
        String choice = System.console().readLine();
        if(choice.equals("1")){
            // verify user by prev password
            // do the actuall delete

            ArrayList<User> users = (ArrayList<User>) db.get("users");

            for (User user : users){
                if(user.getUsername().equals(db.get("currentUserName"))){
                    // found the user to delet
                    // validate by previous password
                    // allow user to delete
                    // take back to login

                    System.out.println("Enter previous password ");
                    String prevPassInput = System.console().readLine();
            
                    if(prevPassInput.equals(user.getPassword())){
                        // user verified to delete

                        // do the actual delete
                        users.remove(user);
                        System.out.println("=========User deleted successfully!=============");
                        handleFirstChoice();
    
                    }else {
                        System.out.println("Previous password dont match");
                        handelUserUpdate();
                    }
                   
    
                 return;
                }
        } 
    }
}

    private static void handelUserUpdate() {
        // if user is hear, it means it already autehncated
        // find and replace user from 'users'
        ArrayList<User> users = (ArrayList<User>) db.get("users");

        for (User user : users){
            if(user.getUsername().equals(db.get("currentUserName"))){
                // found the user to update
                // validate by previous password
                // allow user to change user name and pass

                System.out.println("Enter previous password ");
                String prevPassInput = System.console().readLine();
        
                if(prevPassInput.equals(user.getPassword())){
                    // user verified to update
                    System.out.println("Enter new user name");
                    String newUserNameInput = System.console().readLine();
                    System.out.println("Enter new password");
                    String newPasswordInput = System.console().readLine();

                    // do the actual update

                    user.setPassword(newPasswordInput);
                    user.setUserName(newUserNameInput);
                    // user account update, take back to the log in 
                    System.out.println("=========User updated successfully!=============");
                    handleFirstChoice();

                }else {
                    System.out.println("Previous password dont match");
                    handelUserUpdate();
                }
               

             return;
            }
        }
        

    }


}
