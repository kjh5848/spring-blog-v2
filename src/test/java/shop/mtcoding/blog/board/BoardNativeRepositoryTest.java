package shop.mtcoding.blog.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Import(BoardNativeRepository.class)
@DataJpaTest
class BoardNativeRepositoryTest {


    @Autowired
    private BoardNativeRepository boardNativeRepository;

    @Test
    public void update_test() {
        int id = 1;
        String username = "kjh";
        String title = "수정 1";
        String content = "수정 내용1";

        boardNativeRepository.update(id,username,title,content);

        Board board = boardNativeRepository.findById(id);

        // org.assertj.core.api
        assertThat(board.getId()).isEqualTo(id);
        assertThat(board.getUsername()).isEqualTo(username);
        assertThat(board.getTitle()).isEqualTo(title);
        assertThat(board.getContent()).isEqualTo(content);
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

        List<Board> boardList = boardNativeRepository.findAll();
        System.out.println("findAll_test/size: " + boardList.size());

        boardList.forEach(board ->
                System.out.println("findAll_test/size: " + board.getId())
        );

        // org.assertj.core.api
        assertThat(boardList.size()).isEqualTo(4);
        assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");
    }
}