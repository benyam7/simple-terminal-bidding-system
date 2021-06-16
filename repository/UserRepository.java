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
    public String updateUser(String id) {
        // for(int i = 0; i < this.database.length ; i++){
        //     if(this.database[i].id == id){

        //     }
        // }
        System.out.println("User updated");
        return null;
    }

    @Override
    public String deleteUser(String id) {
        System.out.println("User deleted");
        return null;
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

    
}
