package shop.mtcoding.blog.board;

import jakarta.websocket.server.ServerEndpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.Reply.Reply;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardSerivce {
    private final BoardJPARepository boardJPARepository;


    public Board 글상세보기(int boardId, User sessionUser) {
        Board board = boardJPARepository.findByJoinuser(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));

        board.checkBoardOwner(sessionUser);

        //댓글 주인 확인
        board.getReplies().forEach(reply -> {
            reply.checkReplyOwner(sessionUser);
        });


        return board;
    }

    public List<Board> 목록조회() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return boardJPARepository.findAll(sort);
    }


    @Transactional
    public void 글삭제(int boardId, int sessionUserId) {
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("해당게시글을 찾을 수 없습니다."));

        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글을 삭제할 권한이 없습니다.");
        }
        boardJPARepository.deleteById(boardId);
    }


    public Board 글조회(int boardId) {
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("해당게시글을 찾을 수 없습니다."));
        return board;
    }

    @Transactional
    public void 게시글수정(int boardId, int sessionUserId, BoardRequest.UpdateDTO reqDTO) {
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("해당게시글을 찾을 수 없습니다."));
        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글을 수정할 권한이 없습니다.");
        }
        board.setTitle(reqDTO.getTitle());
        board.setContent(reqDTO.getContent());
    }

    @Transactional
    public void save(BoardRequest.SaveDTO reqDTO, User sessionUser) {
        boardJPARepository.save(reqDTO.toEntity(sessionUser));
    }
}
