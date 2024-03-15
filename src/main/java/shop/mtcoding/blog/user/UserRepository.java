package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    @Transactional
    public User save(User user) {
        em.persist(user);
        return user;
    }

    public User findByUsernameAndPassword(UserRequest.LoginDTO reqDTO) {
        try {
            return em.createQuery("select u from User u where u.username = :username and u.password = :password", User.class)
                    .setParameter("username", reqDTO.getUsername())
                    .setParameter("password", reqDTO.getPassword())
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public User findById(Integer id) {
        User user = em.find(User.class, id);
        return user;
    }

    @Transactional
    public User update(Integer id, UserRequest.UpdateDTO reqDTO) {
        User user = findById(id);
        user.setPassword(reqDTO.getPassword());
        user.setEmail(reqDTO.getEmail());
        return user;
    }

}
