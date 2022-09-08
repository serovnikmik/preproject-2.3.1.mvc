package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.models.User;

import javax.persistence.*;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

//@Component
@Repository
public class UserDAO {

    private static int IDCounter = 2;

    private EntityManager em;

    @Autowired
    public UserDAO(LocalContainerEntityManagerFactoryBean lcemfb){
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

    public void save(User user){
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public void update(int userID, User user){
        try{
            em.getTransaction().begin();
            User userToUpdate = em.find(User.class, userID);
            userToUpdate.setName(user.getName());
            userToUpdate.setAge(user.getAge());
            userToUpdate.setEmail(user.getEmail());
            em.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveUsers(List<User> list){
        System.out.println("Trying to save users");
        System.out.println("Saving users (DAO)");
        try{
            em.getTransaction().begin();
            for (User user : list){
                em.persist(user);
            }
            em.getTransaction().commit();
        } catch (EntityExistsException e){
            System.out.println("User with id of some users exists");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional
    public void deleteAllUsers(){
        try {
            em.getTransaction().begin();

//            String jpql = "DELETE FROM User u";
//            TypedQuery<User> query = em.createQuery(jpql, User.class);
//            query.executeUpdate();

            Query q = em.createQuery("DELETE FROM User user");
            q.executeUpdate();
//            em.createQuery("DELETE FROM user")
//            em.createNativeQuery("truncate table user").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {

        }
    }

    public void delete(int id){
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        em.remove(user);
        em.getTransaction().commit();
    }

//    public void update(int id, User user){
//        em.getTransaction().begin();
//
//        em.getTransaction().commit();
//    }

}
