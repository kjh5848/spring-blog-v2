package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardJPARepository boardJPARepository;

    @Transactional
    public Board 글삭제(Integer boardId, Integer sessionUserId) {
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글을 삭제할 권한이 없습니다.");
        }
        boardJPARepository.deleteById(boardId);
        return board;
    }


    public BoardResponse.DTO 글조회(int boardId) {
        //1. 조회 및 예외처리
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("해당 게시글을 찾을 수 없습니다."));

        //2. 권한 처리
//        if (sessionUserId != board.getUser().getId()) {
//            throw new Exception403("게시글을 수정페이지로 이동할 권한이 없습니다.");
//        }
        return new BoardResponse.DTO(board);
    }

    @Transactional
    public BoardResponse.DTO updateById(int boardId, int sessionUserId, BoardRequest.UpdateDTO reqDTO) {
        //1. 조회 및 예외처리
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        //2. 권한 처리
        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글을 수정할 권한이 없습니다.");
        }
        // 3. 글수정
        board.setTitle(reqDTO.getTitle());
        board.setContent(reqDTO.getContent());
        return new BoardResponse.DTO(board);
    }//더티체킹


    @Transactional
    public BoardResponse.DTO save(BoardRequest.SaveDTO reqDTO, User sessionUser) {
        Board board = boardJPARepository.save(reqDTO.toEntity(sessionUser));
        return new BoardResponse.DTO(board);
    }


    public List<BoardResponse.MainDTO> 글목록조회(int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, 3, sort);
        return boardJPARepository.findAll(pageable).stream().map(BoardResponse.MainDTO::new).toList();
    }

    // board, isOwner
    public BoardResponse.DetailDTO 글상세보기(int boardId, User sessionUser) {
        Board board = boardJPARepository.findByJoinUser(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));

        //댓글 정렬 스트림 솔티드

        return new BoardResponse.DetailDTO(board,sessionUser);
    }
}
