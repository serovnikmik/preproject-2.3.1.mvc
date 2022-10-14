package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();
    User getUser(int userID);

    void save(User user);
    void update(User user);
    void delete(int id);

    void saveUsers(List<User> list);
    void deleteAllUsers();



}
