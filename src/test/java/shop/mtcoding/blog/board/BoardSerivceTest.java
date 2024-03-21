package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.mtcoding.blog.Reply.Reply;
import shop.mtcoding.blog.Reply.ReplyJPARepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BoardSerivceTest {

    @Autowired
    private ReplyJPARepository replyJPARepository;

    @Autowired
    private BoardJPARepository boardJPARepository;

    @Test
    public void replyJoinUser_test() {
        // given
        int id = 4;

        // when
//        Board board = boardJPARepository.findByReplyJoinUser(id).get();
//        System.out.println("board = " + board);

        List<Reply> reply = replyJPARepository.findByBoardId(id);
        System.out.println("reply = " + reply);
        // then

    }

    @Test
    public void find_test(){
        // given
        int id = 1;

        // when
        List<BoardResponse.CountDTO> boardCountDTOList = boardJPARepository.findAllWithReplyCount();
        System.out.println("boardCountDTOList = " + boardCountDTOList);
        // then

    }

}