package web.service;

import org.springframework.stereotype.Service;
import web.models.User;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user);

    void removeUser(long id);

    void updateUser(User user);
}
