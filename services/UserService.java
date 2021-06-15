package services;

import data.models.User;

public interface UserService {
    String createUser(User user);
    String  updateUser(String id);
    String deleteUser(String id);
    User getPublicProfile(String id);
}
