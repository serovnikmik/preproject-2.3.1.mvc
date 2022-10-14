package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.*;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public UserDAOImpl(LocalContainerEntityManagerFactoryBean lcemfb){
        EntityManagerFactory emf = lcemfb.getObject();
        em = emf.createEntityManager();
    }

    public List<User> getAllUsers(){
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u ORDER BY u.id", User.class);
        List<User> result = q.getResultList();
        return result;
    }

    public User getUser(int userID){
        User userToFind = new User();
        try{
            userToFind = em.find(User.class, userID);
        } catch (Exception e){
            e.printStackTrace();
        }
        return userToFind;
    }

    public void save(User user){
        em.persist(user);
    }

    public void update(User user){
        User userToUpdate = em.find(User.class, user.getId());
        userToUpdate.setName(user.getName());
        userToUpdate.setAge(user.getAge());
        userToUpdate.setEmail(user.getEmail());
    }


    public void saveUsers(List<User> list){
        try{
            for (User user : list){
                em.persist(user);
            }
        } catch (EntityExistsException e){
            System.out.println("User with id of some users exists");
        }
    }


    public void deleteAllUsers(){
        Query q = em.createQuery("DELETE FROM User user");
        q.executeUpdate();
    }


    public void delete(int id){
        User user = em.find(User.class, id);
        em.remove(user);
    }

}
