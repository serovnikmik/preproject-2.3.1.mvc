package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDAO;
import web.repository.UserRepository;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public void deleteAllUsers(){
        userDAO.deleteAllUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(int id){
        return userDAO.getUser(id);
    }

    @Override
    @Transactional
    public void save(User user){
        userDAO.save(user);
    }

    @Override
    @Transactional
    public void delete(int id){
        userDAO.delete(id);
    }

    @Override
    @Transactional
    public void update(User user){
        userDAO.update(user);
    }



}
