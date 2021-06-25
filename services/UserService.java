package services;

import java.util.ArrayList;

import data.models.User;

public interface UserService {
    String createUser(User user);
    boolean  updateUser(String id, String username, String password);
    boolean deleteUser(String username);
    User getPublicProfile(String id);
    ArrayList<User> getUsers();
}
