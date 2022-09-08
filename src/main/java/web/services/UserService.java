package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import web.dao.UserDAO;
import web.models.User;

import java.util.ArrayList;
import java.util.List;

//@Component
@Service
public class UserService {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Autowired
    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    public void save(User user){
        userDAO.save(user);
    }

    public void saveExampleUsers(){
        System.out.println("Trying to save users examples...");
        List<User> usersListExample = new ArrayList<>();
        usersListExample.add(new User("Name1", 11, "1@mail.ru"));
        usersListExample.add(new User("Name2", 12, "2@mail.ru"));
        usersListExample.add(new User("Name3", 13, "3@mail.ru"));

        userDAO.saveUsers(usersListExample);
    }

    public User getUser(int id){
        return userDAO.getUser(id);
    }

    public void deleteAllUsers(){
        userDAO.deleteAllUsers();
    }

    public void delete(int id){
        userDAO.delete(id);
    }

    public void update(int id, User user){
        userDAO.update(id, user);
    }

}
