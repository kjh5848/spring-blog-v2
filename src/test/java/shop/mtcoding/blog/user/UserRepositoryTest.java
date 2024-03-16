package shop.mtcoding.blog.user;

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