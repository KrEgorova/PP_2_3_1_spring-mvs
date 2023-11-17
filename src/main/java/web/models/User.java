package web.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;


@Entity
@Table(name = "users")
@Component
public class User {
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "city")
    private String city;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public User(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public User() {

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }
}
