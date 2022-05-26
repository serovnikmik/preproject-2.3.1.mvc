package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import web.dao.UserDAO;
import web.models.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    public void saveExampleUsers(){
        List<User> usersListExample = new ArrayList<>();
        usersListExample.add(new User("Name1", 11));
        usersListExample.add(new User("Name2", 12));
        usersListExample.add(new User("Name3", 13));

        userDAO.saveUsers(usersListExample);
    }

}
