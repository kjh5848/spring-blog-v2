package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJPARepository userJPARepository;

    public User 회원조회(int sessionUserId) {
        return userJPARepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다."));
    }

    @Transactional
    public User 회원수정(UserRequest.UpdateDTO reqDTO, int sessionUserId) {
        User user = userJPARepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다."));
        user.setPassword(reqDTO.getPassword());
        user.setEmail(reqDTO.getEmail());

        return user;
    }

    @Transactional
    public void 회원가입(UserRequest.JoinDTO reqDTO) {
        Optional<User> userOP = userJPARepository.findByUsername(reqDTO.getUsername());
        if (userOP.isPresent()) {
            throw new Exception400("중복된 유저네입입니다.");
        }
        userJPARepository.save(reqDTO.toEntity());
    }

    public User 로그인(UserRequest.LoginDTO reqDTO) {
        return  userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않습니다."));

    }


}
