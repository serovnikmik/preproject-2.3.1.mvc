package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;
import web.models.User;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {

    private static int IDCounter = 2;

    private EntityManager em;

    @Autowired
    public UserDAO(LocalContainerEntityManagerFactoryBean lcemfb){
        EntityManagerFactory emf = lcemfb.getObject();
        em = emf.createEntityManager();
    }

    public List<User> getAllUsers(){
        List<User> res = em
                .createQuery("Select user from User user", User.class)
                .getResultList();
        System.out.println(res.size());
        return res;
    }

    public void saveUser(User user){
        if (user.getId() == 0){
            user.setId(this.IDCounter++);
        }
        try {
            em.getTransaction();
            em.persist(user);
            em.getTransaction().commit();
        } catch (EntityExistsException e){
            System.out.println("User with this id already exists");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveUsers(List<User> list){
        try{
            em.getTransaction().begin();
            for (User user : list){
                if (user.getId() == 0){
                    user.setId(this.IDCounter++);
                }
                em.persist(user);
            }
            em.getTransaction().commit();
        } catch (EntityExistsException e){
            System.out.println("User with id of some users exists");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public User getUser(int id){
        User user = em.find(User.class, id);
        return user;
    }

}
