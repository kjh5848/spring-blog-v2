package shop.mtcoding.blog.user;

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