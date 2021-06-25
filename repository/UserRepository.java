package repository;

import java.util.ArrayList;

import data.Database;
import services.UserService;
import data.models.User;

public class UserRepository   implements UserService, Repository{

    private Database  db;

    public UserRepository(Database db){
        this.db = db;
    }
    @Override
    public String createUser(User user) {
        // this.database.add(user);
        ArrayList<User> users = (ArrayList<User>) this.db.get("users");
        users.add(user);
        System.out.println("User created");
        return null;
    }

    @Override
    public boolean updateUser(String id, String username, String password) {
     
        ArrayList<User> users = (ArrayList<User>) db.get("users");
        for (User user : users){
            if(user.getId().equals(id)){
                user.setPassword(password);
                user.setUserName(username);
            }}
            
        return true;
    }

 

    @Override
    public User getPublicProfile(String id) {
        System.out.println("got user");
        return null;
    }

    public void setUpRepo(String collectionName, ArrayList<User> collection) {
       this.db.put(collectionName, collection);
       this.db.put("currentUserName", "" );
        System.out.println("database set up");
    }
    // @Override
    // public ArrayList<User> getUsers() {
        // return (ArrayList<User>) this.db.get("users");
    // }
  
    @Override
    public ArrayList<User> getUsers() {
        
        ArrayList<User> users =  (ArrayList<User>) this.db.get("users");
        return users;   
    }
    @Override
    public boolean deleteUser(String username) {
       
        ArrayList<User> users = (ArrayList<User>) db.get("users");

            for (User user : users){
                if(user.getUsername().equals(username)){
                    // found the user to delet
                    // validate by previous password
                    // allow user to delete
                    // take back to login
                    users.remove(user);
                    return true;
                }
            }
            return false;
    
        
    }

    
}
