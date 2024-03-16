package shop.mtcoding.blog.user;

import jakarta.el.ELManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Transient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;


    @Transactional//내가 만든게 아니라서 테스트 안함.
    public User save(User user) {
        em.persist(user);
        return user;
    }

    public User findByUsernameAndPassword(UserRequest.LoginDTO reqDTO) {
        return em.createQuery("select u from User u  where u.username = :username and u.password = :password", User.class)
                .setParameter("username", reqDTO.getUsername())
                .setParameter("password", reqDTO.getPassword())
                .getSingleResult();
    }

}
