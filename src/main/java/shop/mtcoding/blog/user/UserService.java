package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog._core.utills.ApiUtil;

import java.util.Optional;

@RequiredArgsConstructor
@Service // IOC에 등록
public class UserService {
    private final UserJPARepository userJPARepository;
    private final HttpSession session;

    @Transactional
    public User 회원수정(UserRequest.UpdateDTO reqDTO, int id) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다."));
        user.setPassword(reqDTO.getPassword());
        user.setEmail(reqDTO.getEmail());
//        userJPARepository.save(user);//해도되고 안해도 된당.
        return user;
    }//더티체킹


    public UserResponse.DTO 회원조회(int id) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다."));
        return new UserResponse.DTO(user);
    }

    public User 로그인(UserRequest.LoginDTO reqDTO) {
        //해시검사가 들어와야 함.
        User sessionUser = userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));
        return sessionUser;
    }

    @Transactional
    public UserResponse.DTO 회원가입(UserRequest.JoinDTO reqDTO) {
        //1. 유효성 검사(컨틑롤러 책임)
        //2. 유저네임 중복검사(서비스 체크) - DB연결이 필요함.
        Optional<User> userOP = userJPARepository.findByUsername(reqDTO.getUsername());

        if (userOP.isPresent()) { //
            throw new Exception400("중복된 유저네임입니다.");
        }
        User user =  userJPARepository.save(reqDTO.toEntity());
        //트라이캐치로 잡을 수 있지만 명확한 오류가 낫을때 확인할 수 없기 때문에 추천하지 않는다.
        return new UserResponse.DTO(user);
    }

}
