package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(BoardPersistRepository.class)
@DataJpaTest
class BoardpersistRepositoryTest {
    @Autowired
    private BoardPersistRepository boardpersistRepository;

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