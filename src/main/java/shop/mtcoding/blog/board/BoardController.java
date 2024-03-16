package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog.user.User;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;
    private final HttpSession session;

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardRepository.findById(id);
        if (sessionUser.getId() == board.getUser().getId()) {
            throw new Exception403("게시글을 삭제할 권한이 없습니다.~~");
        }

        boardRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id,BoardRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardRepository.findById(id);
        if (sessionUser.getId() == board.getUser().getId()) {
            throw new Exception403("게시글을 수정할 권한이 없습니다.~~");
        }

        boardRepository.updateById(id,reqDTO.getTitle(), reqDTO.getContent());
        return "redirect:/board/" + id;
    }

    @PostMapping("/board/save")
    public String sava(BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardRepository.save(reqDTO.toEntity(sessionUser));
        return "redirect:/";
    }


    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest req) {
        Board board = boardRepository.findById(id);

        if (board == null) {
            throw new Exception401("해당 게시글을 찾을 수 없습니다.");
        }

        req.setAttribute("board", board);
        return "/board/update-form";
    }

    @GetMapping("/")
    public String index(HttpServletRequest req) {

        List<Board> boardList = boardRepository.findAll();
        req.setAttribute("boardList", boardList);
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {

        return "/board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id,HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardRepository.findById(id);
        req.setAttribute("board", board);

        boolean isOwner = sessionUser.getId() == board.getUser().getId() ? true : false;
        req.setAttribute("isOwner", isOwner);

        return "board/detail";
    }
}
