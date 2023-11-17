package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserDaoImpl implements UserDao {
    @PersistenceContext(unitName = "entityManagerFactoryBean")
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        List<User> users = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
        users.sort(Comparator.comparingLong(User::getId));
        return users;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void removeUser(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}
