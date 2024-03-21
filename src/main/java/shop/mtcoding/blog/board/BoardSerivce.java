package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardSerivce {
    private final BoardJPARepository boardJPARepository;


    public BoardResponse.DetailDTO 글상세보기(int boardId, User sessionUser) {
        Board board = boardJPARepository.findByJoinuser(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));


        return new BoardResponse.DetailDTO(board,sessionUser);
    }

    public List<BoardResponse.MainDTO> 목록조회() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return boardJPARepository.findAll(sort).stream().map(BoardResponse.MainDTO::new).toList();
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


    public BoardResponse.DTO 글조회(int boardId) {
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("해당게시글을 찾을 수 없습니다."));
        return new BoardResponse.DTO(board);
    }

    @Transactional
    public BoardResponse.DTO 게시글수정(int boardId, int sessionUserId, BoardRequest.UpdateDTO reqDTO) {
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("해당게시글을 찾을 수 없습니다."));
        if (sessionUserId != board.getUser().getId()) {
            System.out.println("sessionUserId = " + sessionUserId);
            System.out.println("board.getUser().getId() = " + board.getUser().getId());
            throw new Exception403("게시글을 수정할 권한이 없습니다.");
        }
        board.setTitle(reqDTO.getTitle());
        board.setContent(reqDTO.getContent());
        return  new BoardResponse.DTO(board);
    }

    @Transactional
    public BoardResponse.DTO save(BoardRequest.SaveDTO reqDTO, User sessionUser) {
        Board board = boardJPARepository.save(reqDTO.toEntity(sessionUser));
        System.out.println("board = " + board);
        return new BoardResponse.DTO(board);
    }
}
