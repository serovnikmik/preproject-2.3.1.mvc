package web.models;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@Component
@Entity
@Table(name="user")
public class User{

    @Id
    @Column(name="user_id")
    private int Id;

    @Column(name="name")
    private String name;

    @Column(name="age")
    private int age;

    public User(){

    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User(int id, String name, int age) {
        this.name = name;
        this.age = age;
        this.Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
