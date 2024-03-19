package shop.mtcoding.blog.Reply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.user.User;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;
    private final HttpSession session;

    @PostMapping("/reply/save")
    public String save(ReplyRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.댓글쓰기(reqDTO, sessionUser);
        return "redirect:/board/"+ reqDTO.getBoardId();
    }

    @PostMapping("/reply/{id}/delete")
    public String delete(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        int boardId = replyService.댓글삭제(id,sessionUser.getId());
        return "redirect:/board/" + boardId;
    }
}