package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.User;

//@Component
//@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
