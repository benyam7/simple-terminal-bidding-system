package repository;

import data.Database;
import services.UserService;
import data.models.User;

public class UserRepository implements UserService {

    private Database  database;

    public UserRepository(Database db){
        this.database = db;
    }
    @Override
    public String createUser(User user) {
        // this.database.add(user);
        System.out.println("User Added");
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

    
}
