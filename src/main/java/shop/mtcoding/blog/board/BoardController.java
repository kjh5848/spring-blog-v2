package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardSerivce boardSerivce;
    private final HttpSession session;

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, BoardRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardSerivce.게시글수정(id, sessionUser.getId(), reqDTO);
        return "redirect:/board/{id}";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest req) {
        Board board = boardSerivce.글조회(id);
        req.setAttribute("board", board);
        return "board/update-form";
    }

    @RequestMapping(value = "/board/{id}/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public String delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardSerivce.글삭제(id, sessionUser.getId());
        return "redirect:/";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardSerivce.save(reqDTO,sessionUser);
        return "redirect:/";
    }


    @GetMapping("/")
    public String index(HttpServletRequest req) {
        List<Board> boardList = boardSerivce.목록조회();
        req.setAttribute("boardList", boardList);
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "/board/save-form";
    }


    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardSerivce.글상세보기(id, sessionUser);

        req.setAttribute("board", board);

        return "/board/detail";
    }
}
