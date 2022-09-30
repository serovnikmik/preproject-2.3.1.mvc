package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDAO;
import web.models.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Autowired
    public UserServiceImpl(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    @Transactional
    public void save(User user){
        userDAO.save(user);
    }

    public User getUser(int id){
        return userDAO.getUser(id);
    }

    public void deleteAllUsers(){
        userDAO.deleteAllUsers();
    }

    @Transactional
    public void delete(int id){
        userDAO.delete(id);
    }

    @Transactional
    public void update(User user){
        userDAO.update(user);
    }

}
