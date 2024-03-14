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
    public void save(User user) {
        em.persist(user);
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
}
