package web.dao;

import org.springframework.stereotype.Repository;
import web.models.User;

import java.util.List;

@Repository
public interface UserDao {
    List<User> getAllUsers();

    void saveUser(User user);

    void removeUser(long id);

    void updateUser(User user);
}
