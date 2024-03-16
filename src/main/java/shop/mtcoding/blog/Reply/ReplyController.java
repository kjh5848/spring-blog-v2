package shop.mtcoding.blog.Reply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardRepository;
import shop.mtcoding.blog.user.User;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyRepository replyRepository;
    private final HttpSession session;
    private final BoardRepository boardRepository;

    @PostMapping("/reply/{id}/delete")
    public String delete(@PathVariable Integer id) {
        Reply reply = replyRepository.findById(id);
        System.out.println(reply);
        replyRepository.deleteById(id);

        return "redirect:/board/" + reply.getBoard().getId();
    }

    @PostMapping("/reply/save")
    public String save(@RequestParam Integer boardId, ReplyRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardRepository.findById(boardId);
        replyRepository.save(reqDTO.toEntity(sessionUser, board));
        return "redirect:/board/" + boardId;
    }

}
