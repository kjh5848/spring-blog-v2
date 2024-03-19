package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;
import org.assertj.core.api.Assertions;
import org.hibernate.query.criteria.JpaQueryStructure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Import(UserRepository.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager em;

    @Test
    public void update_test(){
        // given
        int id = 1;
        String password = "4444";
        String email = "kjh@nate.com";
        UserRequest.UpdateDTO reqDTO = new UserRequest.UpdateDTO();
        reqDTO.setPassword(password);
        reqDTO.setEmail(email);
        // when
        userRepository.update(id,reqDTO);
        em.flush();
        // then

    }

    @Test
    public void findById_test(){
        // given
        int id = 1;
        // when
        userRepository.findById(id);

        // then

    }

    @Test
    public void findByUsername_test(){
        // given
        String username = "ssar";
        String password = "1234";

        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO(
                username,password
        );

        // when
        User user = userRepository.findByUsernameAndPassword(reqDTO);
        // then
        assertThat(user.getUsername()).isEqualTo("ssar");

    }

    @Test
    public void save_test(){
        // given
        String username = "kjh";
        String password = "1234";
        String email = "kjh@nate,com";
        User user = new User(username, password, email);
        userRepository.save(user);

//        assertThat()
        // when


        // then

    }
}