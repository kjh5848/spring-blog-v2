package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
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
        public void findById_test(){
            // given
            int id = 1;
            // when
        User user = userRepository.findById(id);
        System.out.println("user_test = " + user);
            // then

        }

    @Test
    public void updateById_test() {
        // given
        int id = 1;
        String username = "rrr";
        String password = "1234";
        String email = "rrr@nate.com";

        // when
        userRepository.updateById(id, username, password, email);
        em.flush();
        // then

    }


    @Test
    public void findByUsernameAndPassword_test() {
        // given
        String username = "rrr";
        String password = "1234";

        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO(
                username, password
        );

        // when
        User user = userRepository.findByUsernameAndPassword(reqDTO);
        // then
        assertThat(user.getUsername()).isEqualTo("qqq");
    }

}