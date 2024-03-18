package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;

//자동 컴포넌트 스캔이 된다.
public interface UserJPARepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.username=:username AND u.password = :password")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}



