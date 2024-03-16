package shop.mtcoding.blog.Reply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.user.User;

import static org.junit.jupiter.api.Assertions.*;

@Import(ReplyRepository.class)
@DataJpaTest
class ReplyRepositoryTest {
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void save_test() {

        // given
        int userId = 1;
        int boardId = 1;
        String content = "제목 1에 댓글 1";

        User user = new User();
        user.setId(userId);
        Board board = new Board();
        board.setId(boardId);
        // when
        ReplyRequest.SaveDTO reqDTO = new ReplyRequest.SaveDTO(
                content
        );
        Reply reply = replyRepository.save(reqDTO.toEntity(user, board));
        System.out.println("reply_test = " + reply);
        // then


    }

}