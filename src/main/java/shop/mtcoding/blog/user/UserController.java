package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.board.BoardRepository;
import shop.mtcoding.blog.board.BoardRequest;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final HttpSession session;

    @PostMapping("/user/{id}/update")
    public String update(@PathVariable int id,UserRequest.UpdateDTO reqDTO) {


        userRepository.update(id, reqDTO);


        return "redirect:/index";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO) {
        User sessionUser = userRepository.findByUsernameAndPassword(reqDTO);
        if (sessionUser == null) {
            return "redirect:/login-form";
        }
        session.setAttribute("sessionUser", sessionUser);

        return "redirect:/";
    }

    @PostMapping("/join")
    public String join(UserRequest.SaveDTO reqDTO) {
        userRepository.save(reqDTO.toEntity());
        return "redirect:/login-form";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "/user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "/user/login-form";
    }

    @GetMapping("/user/update-form")
    public String updateForm() {
        return "/user/update-form";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
