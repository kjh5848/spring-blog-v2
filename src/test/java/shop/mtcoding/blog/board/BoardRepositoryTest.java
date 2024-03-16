package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.user.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@Import(BoardRepository.class)
@DataJpaTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;

    @Test
        public void findAll_test(){
            // given

            // when
        List<Board> boardList = boardRepository.findAll();
        boardList.forEach(System.out::println);
            // then

        }

    @Test
    public void deleteById_test() {
        // given
        int id = 1;
        // when
        boardRepository.deleteById(id);
        em.flush();
        // then

    }

    @Test
    public void update_test() {
        // given
        int id = 1;
        String title = "제목수정1 ";
        String content = "내용수정1 ";
        // when
        boardRepository.updateById(id, title, content);
        em.flush();
        // then

    }

    @Test
    public void findById_test() {
        // given
        int id = 1;
        // when
        Board board = boardRepository.findById(id);
        // then
        assertThat(board.getId()).isEqualTo(1);
    }
}