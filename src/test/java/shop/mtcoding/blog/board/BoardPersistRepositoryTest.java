package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@Import(BoardPersistRepository.class)
@DataJpaTest
class BoardPersistRepositoryTest {

    @Autowired
    private BoardPersistRepository boardpersistRepository;

    @Autowired
    private EntityManager em;


    @Test
    public void update_test() {
        int id = 1;
        String username = "kjh";
        String title = "수정 1";
        String content = "수정 내용1";

    }


    @Test
    public void findeAll_test() {

        List<Board> boardList = boardpersistRepository.findAll();
        System.out.println("findAll_test/size: " + boardList.size());

        boardList.forEach(board ->
                System.out.println("findAll_test/size: " + board.getId())
        );

        // org.assertj.core.api
        assertThat(boardList.size()).isEqualTo(4);
        assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");
    }

    @Test
    public void findeById_test() {
        int id = 1;

        Board board = boardpersistRepository.findById(id);
        Board board1 = boardpersistRepository.findById(id);
        Board board2 = boardpersistRepository.findById(id);
        Board board3 = boardpersistRepository.findById(id);
        Board board4 = boardpersistRepository.findById(id);
        Board board5 = boardpersistRepository.findById(id);
        Board board6 = boardpersistRepository.findById(id);
        Board board7 = boardpersistRepository.findById(id);

        System.out.println("findById_test/size: " + board);
        System.out.println("findById_test/size: " + board2);
        System.out.println("findById_test/size: " + board3);
        System.out.println("findById_test/size: " + board4);
        System.out.println("findById_test/size: " + board5);
        System.out.println("findById_test/size: " + board6);
        System.out.println("findById_test/size: " + board7);

        // org.assertj.core.api
    }

    @Test
    public void delete_test() {
        int id = 1;
        boardpersistRepository.deleteById(id);

    }



}