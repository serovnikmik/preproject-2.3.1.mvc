package web.services;

import web.models.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();
    public void deleteAllUsers();

    public User getUser(int id);
    public void save(User user);
    public void delete(int id);
    public void update(User user);

}
