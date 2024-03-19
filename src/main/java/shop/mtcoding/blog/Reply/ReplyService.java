package shop.mtcoding.blog.Reply;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardJPARepository;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyJPARepository replyJPARepository;
    private final BoardJPARepository boardJPARepository;
    private final EntityManager em;

    @Transactional
    public void 댓글쓰기(ReplyRequest.SaveDTO reqDTO, User sessionUser) {
        Board board = boardJPARepository.findById(reqDTO.getBoardId())
                .orElseThrow(() -> new Exception404("없는 게시글에는 댓글을 작성할 수 없어요."));

        Reply reply = reqDTO.toEntity(sessionUser, board);
        replyJPARepository.save(reply);
    }

    @Transactional
    public int 댓글삭제(Integer replyId, Integer sessionUserId) {
        Reply reply = replyJPARepository.findById(replyId)
                .orElseThrow(() -> new Exception404("해당 댓글을 찾을 수 없습니다."));
        int boardId = reply.getBoard().getId();
        if (sessionUserId != reply.getUser().getId()) {
            throw new Exception403("댓글을 삭제할 권한이 없습니다.");
        }
        replyJPARepository.deleteById(replyId);
        return boardId;
    }
}
