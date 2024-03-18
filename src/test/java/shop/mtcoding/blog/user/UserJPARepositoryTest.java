package shop.mtcoding.blog.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//임포트 필요없음, 적어도 된다.
@DataJpaTest
class UserJPARepositoryTest {

    @Autowired
    private UserJPARepository userJPARepository;
    
    
    @Test
    //네임드 쿼리
    public void findByUsernameAndPassword_test(){
        // given
        String username = "ssar";
        String password = "1234";
    
        // when
        userJPARepository.findByUsernameAndPassword(username, password);

        // then
        
    }

    @Test
    public void findAll_test() throws JsonProcessingException {
        // given

        // when
        //Sort = order by id desc를 할 수 있다.
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, 3, sort);
        Page<User> userPG = userJPARepository.findAll(pageable);
        System.out.println("userPG _test = " + userPG);
        // then
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(userPG);
        System.out.println("json _test = " + json);
    }

    @Test
    public void findById_test(){
        // given
        int id = 5;


        // when
        Optional<User> userOp = userJPARepository.findById(id);

        if (userOp.isPresent()) {
            User user = userOp.get();
            System.out.println("user _test: " + user.getUsername());
        }

        // then

    }

    @Test
    public void save_test() {
        // given
        User user = User.builder()
                .username("happy")
                .password("1234")
                .email("happy@nate.com")
                .build();

        // when
        userJPARepository.save(user);

        // then

    }
}