package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import({BoardNativeRepository.class, BoardPersistRepository.class})
@DataJpaTest
class BoardNativeRepositoryTest {
    @Autowired
    private BoardNativeRepository boardNativeRepository;

    @Autowired
    private BoardPersistRepository boardpersistRepository;

    @Test
    public void save_test() {
        Board board = new Board("kjh", "제목5", "내용5");

        boardpersistRepository.save(board);
        System.out.println("save_test: " + board);
//        assertThat(board.getUsername()).isEqualTo(username);
//        assertThat(board.getTitle()).isEqualTo(title);
//        assertThat(board.getContent()).isEqualTo(content);
    }

    @Test
    public void update_test() {
        int id = 5;
        String username = "kjh";
        String title = "수정 1";
        String content = "수정 내용1";
        Board board = new Board(username,title,content);
        boardNativeRepository.update(id,board);

        Board boardUpdate = boardNativeRepository.findById(id);

        // org.assertj.core.api
        assertThat(boardUpdate.getId()).isEqualTo(id);
        assertThat(boardUpdate.getUsername()).isEqualTo(username);
        assertThat(boardUpdate.getTitle()).isEqualTo(title);
        assertThat(boardUpdate.getContent()).isEqualTo(content);
    }

    @Test
    public void delete_test() {
        int id = 1;

        boardNativeRepository.deleteById(id);

        List<Board> boardList = boardNativeRepository.findAll();

        // org.assertj.core.api
        assertThat(boardList.size()).isEqualTo(4);

    }

    @Test
    public void findeById_test() {
        int id = 1;

        Board board = boardNativeRepository.findById(id);

        System.out.println("findById_test/size: " + board);

        // org.assertj.core.api
        assertThat(board.getId()).isEqualTo(1);
        assertThat(board.getUsername()).isEqualTo("ssar");
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
}