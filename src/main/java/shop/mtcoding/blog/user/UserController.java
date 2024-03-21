package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.utills.ApiUtil;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final HttpSession session;

    //TODO: 회원정보 조회 API 필요 -> @GetMapping("/api/users/{id}") public String userinfo() {return "redirect:/";}
    @GetMapping("/api/users/{id}")
    public ResponseEntity<?> userinfo(@PathVariable int id) {
        UserResponse.DTO respDTO =  userService.회원조회(id);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }


    //{id}관리자인 경우에 필요하다.
    //외부에서 주소를 이해하기 좋다.
    @PostMapping("/api/users/{id}")
    public ResponseEntity<?> update(@RequestBody UserRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.회원수정(reqDTO, sessionUser.getId());
        session.setAttribute("sessionUser", newSessionUser);

        UserResponse.DTO respDTO = new UserResponse.DTO(newSessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO reqDTO) {
        User sessionUser = userService.로그인(reqDTO);
        session.setAttribute("sessionUser", sessionUser);
        return ResponseEntity.ok(new ApiUtil(null));
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO reqDTO) {
        UserResponse.DTO respDTO = userService.회원가입(reqDTO);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        session.invalidate();
        return ResponseEntity.ok(new ApiUtil(null));
    }
}
