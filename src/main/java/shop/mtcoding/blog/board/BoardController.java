package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog.user.User;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final HttpSession session;

    //TODO: 글조회 API 필요 -> @GetMapping("/")
    //TODO: 글목록조회 API 필요 -> @GetMapping("/api/boards/{id}/detail")
    //TODO: 글상세보기 API 필요 -> @GetMapping("/api/boards/{id}")

    @PutMapping("/api/boards/{id}")
    public String update(@PathVariable Integer id, BoardRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.updateById(id, sessionUser.getId(), reqDTO);
        return "redirect:/board/{id}";
    }

    @DeleteMapping( "/api/boards/{id}")
    public String delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        boardService.글삭제(id, sessionUser.getId());
        return "redirect:/";
    }

    @PostMapping("/api/boards")
    public String save(BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.save(reqDTO, sessionUser);
        return "redirect:/";
    }

}
