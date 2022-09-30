package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import web.models.User;

import javax.persistence.*;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    private EntityManager em;

    @Autowired
    public UserDAOImpl(LocalContainerEntityManagerFactoryBean lcemfb){
        EntityManagerFactory emf = lcemfb.getObject();
        em = emf.createEntityManager();
    }

    public List<User> getAllUsers(){
        em.getTransaction().begin();
        List<User> results = em
                .createQuery("Select u from User u", User.class)
                .getResultList();
        em.getTransaction().commit();
        return results;
    }

    public User getUser(int userID){
        em.getTransaction().begin();
        User userToFind = new User();
        try{
            userToFind = em.find(User.class, userID);
        } catch (Exception e){
            e.printStackTrace();
        }
        em.getTransaction().commit();
        return userToFind;
    }

    @Transactional
    public void save(User user){
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Transactional
    public void update(User user){
        em.getTransaction().begin();
        User userToUpdate = em.find(User.class, user.getId());
        userToUpdate.setName(user.getName());
        userToUpdate.setAge(user.getAge());
        userToUpdate.setEmail(user.getEmail());
        em.getTransaction().commit();
    }

    @Transactional
    public void saveUsers(List<User> list){
        try{
            em.getTransaction().begin();
            for (User user : list){
                em.persist(user);
            }
            em.getTransaction().commit();
        } catch (EntityExistsException e){
            System.out.println("User with id of some users exists");
        }
    }

    @Transactional
    public void deleteAllUsers(){
        em.getTransaction().begin();

        Query q = em.createQuery("DELETE FROM User user");
        q.executeUpdate();
        em.getTransaction().commit();
    }

    @Transactional
    public void delete(int id){
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        em.remove(user);
        em.getTransaction().commit();
    }

}
