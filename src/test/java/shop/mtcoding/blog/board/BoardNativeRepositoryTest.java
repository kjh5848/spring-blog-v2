package shop.mtcoding.blog.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Import(BoardNativeRepository.class)
@DataJpaTest
class BoardNativeRepositoryTest {


    @Autowired
    private BoardNativeRepository boardNativeRepository;

    @Test
    public void findeAll_test() {

        List<Board> boardList = boardNativeRepository.findAll();
        System.out.println("findAll_test/size: " + boardList.size());

        boardList.forEach(board ->
                System.out.println("findAll_test/size: " + board.getId())
        );

        // org.assertj.core.api
        Assertions.assertThat(boardList.size()).isEqualTo(4);
        Assertions.assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");
    }
}