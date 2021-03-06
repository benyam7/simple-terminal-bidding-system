import java.util.ArrayList;
import java.util.Date;

import data.Database;
import data.models.*;
import repository.AuctionRepository;
import repository.RepositoryFactory;
import repository.UserRepository;

public class Main {
    
    private static  Database db;
    private static AuctionRepository auctionRepo;
    private static UserRepository userRepo;
    
    public static void main(String[] args){
        System.out.println("Welcome to Bidding System");
         // setup db
        // setUpDb();
        // handleFirstChoice();
        System.out.println(computeJoinPoint(57, 78));


    }

    public static int computeJoinPoint(int s1, int s2){
        int jS1 = s1;
        int jS2 = s2;


        while (jS1 != jS2){

            String s1S = ""+ jS1;
            String s2S = ""+ jS2;
            String[] s1Arr = s1S.split("");
            String[] s2Arr = s2S.split("");

            for (String x: s1Arr){
                jS1 += Integer.parseInt(x);
            }

            for (String x: s2Arr){
                jS2 += Integer.parseInt(x);
            }
        }

        return jS1;
    }

    private static void handleFirstChoice() {
     
        // // ask user if there account
        askUserForAccount();
        // // if login 
        //    // go to auction system
        //       handleAuctionSystem();
        //    // got to user settings
        //        handleUserSettings();

        // // if no account  db.put("users", new ArrayList<User>());
       db.put("currentUserName", "" );
       System.out.println("database set up");
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
        // ArrayList<User> users =  (ArrayList<User>) db.get("users");
        ArrayList<User> users =  userRepo.getUsers();
    
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
       RepositoryFactory  factory = new RepositoryFactory();

       auctionRepo = (AuctionRepository) factory.getRepository("auctionRepository", db);
       userRepo = (UserRepository) factory.getRepository("userRepository", db);

    //    db.put("users", new ArrayList<User>());
    //    ArrayList<User> users = (ArrayList) db.get("users");
    //    users.add(new User(
    //      "username",
    //  " password",
    //     0, 0, 0
    //    ));

       userRepo.setUpRepo("users", new ArrayList<User>());
       auctionRepo.setUpRepo("auctions",  new ArrayList<Auction>());
      

      System.out.println(db.toString());
    }

    private static void handleAccountCreation() {
        // allow user to enter username
        System.out.println("Enter your username\nUsename: ");

        String usernameInput = System.console().readLine();
        
       
        // check if username already exists
        // get all users and loop
        // ArrayList<User> users =  (ArrayList<User>) db.get("users");
        ArrayList<User> users =  userRepo.getUsers();
    

        // check if user already exists
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
        userRepo.createUser(new User(usernameInput.toString() , passwordInput.toString(), 0, 0, 0));
        // users.add(new User(usernameInput.toString() , passwordInput.toString(), 0, 0, 0));

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

        // just bid on it
        boolean isAuctionIdCorrect = auctionRepo.bid( new Bid((String)db.get("currentUserName"), itemIdInput, Double.parseDouble( bidAmoutInput)));
        // get list of auctions first
        
        
      // validate if the acution doesnt' exist anymore, redo the thing
         if(isAuctionIdCorrect == false){
            System.out.println("Auction id doesn't match");
         }
        // 
        handleAuctionSystem();

    }

    private static void handelListOfAuctions() {
        System.out.println("Current list of auctions");
        // get the auctions form db
        ArrayList<Auction> auctions = auctionRepo.getAuctions();
        
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
        // ArrayList<Auction> auctions = (ArrayList<Auction>) db.get("auctions");
        // ArrayList<Auction> auctions = auctionRepo.getAuctions();
        
        // store auction to db
        auctionRepo.addAuction(new Auction(new Item(
            nameInput,
            descriptionInput
        ), (String) db.get("currentUserName"), new ArrayList<Bid>(), new Date(), endTime, initialPriceInput));

        // auctions.add(new Auction(new Item(
        //     nameInput,
        //     descriptionInput
        // ), (String) db.get("currentUserName"), new ArrayList<Bid>(), new Date(), endTime, initialPriceInput));
        

        

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

            System.out.println("Enter previous password ");
            String prevPassInput = System.console().readLine();
            ArrayList<User> users = userRepo.getUsers();
            for (User user: users){
                if(((String)db.get("currentUserName")).equals(user.getUsername())){
                    if (user.getPassword().equals(prevPassInput)){
                        userRepo.deleteUser(user.getUsername());
                        
                         System.out.println("=========User deleted successfully!=============");
                         handleFirstChoice();
                        
                    }else{
                        System.out.println("Previous password dont match");
                        handelUserDelete();
                       }
                return;
                }
            }
          
        } 
    }


    private static void handelUserUpdate() {
        // if user is hear, it means it already autehncated
        // find and replace user from 'users'
        System.out.println("Enter previous password ");
        String prevPassInput = System.console().readLine();
        ArrayList<User> users = (ArrayList<User>) db.get("users");
        for (User user : users){
            if(user.getUsername().equals(db.get("currentUserName"))){
                if(prevPassInput.equals(user.getPassword())){
                    System.out.println("Enter new user name");
                    String newUserNameInput = System.console().readLine();
                    System.out.println("Enter new password");
                    String newPasswordInput = System.console().readLine();
                    userRepo.updateUser(user.getId(), newUserNameInput, newPasswordInput);
     
                    // user account update, take back to the log in 
                    System.out.println("=========User updated successfully!=============");
                    handleFirstChoice();
            
       
    } else {
        System.out.println("Previous password dont match");
        handelUserUpdate();
    }
}}
            
        }
        

    }
