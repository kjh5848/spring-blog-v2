package shop.mtcoding.blog.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import shop.mtcoding.blog.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BoardJPARepositoryTest {
    @Autowired
    private BoardJPARepository boardJPARepository;
    @Autowired
    private EntityManager em;

    @Test
    public void deleteById_test() {
        // given
        int id = 1;

        // when
        boardJPARepository.deleteById(id);
        em.flush();
        // then

    }

    @Test
    public void findAllPG_test() throws JsonProcessingException {
        // given

        // when
        //Sort = order by id desc를 할 수 있다.
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, 4, sort);
        Page<Board> boardPG = boardJPARepository.findAll(pageable);
        System.out.println("boardPG _test = " + boardPG);

        // Board 객체와 연관된 User 객체를 함께 JSON으로 포함시키기
        List<Board> boardsWithUserDTO = new ArrayList<>();
        for (Board board : boardPG) {
            Board boardWithUserDTO = new Board();
            boardWithUserDTO.setId(board.getId());
            boardWithUserDTO.setTitle(board.getTitle());

            User user = board.getUser();
            if (user != null) {
                User userDTO = new User();
                userDTO.setId(user.getId());
                userDTO.setUsername(user.getUsername());
                userDTO.setEmail(user.getEmail());


                boardWithUserDTO.setUser(userDTO);
            }
            boardsWithUserDTO.add(boardWithUserDTO);
        }

        // then
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(boardsWithUserDTO);
        System.out.println("json = " + json);
    }

    @Test
    public void findAll_test() {
        // given

        // when
        //Sort = order by id desc를 할 수 있다.
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        List<Board> boardList = boardJPARepository.findAll(sort);

        System.out.println("boardList = " + boardList);
    }

    @Test
    public void findByJoinUser_test() {
        // given
        int id = 1;

        // when
        Optional<Board> board = boardJPARepository.findByJoinUser(id);

        // then
        System.out.println("board.getTitle() = " + board);
    }

    @Test
    public void findById_test() {
        // given
        int id = 1;
        // when
        Optional<Board> boardOp = boardJPARepository.findById(id);

        if (boardOp.isPresent()) {
            Board board = boardOp.get();
            System.out.println("user _test: " + board.getTitle());
        }

        // then

    }

    @Test
    public void save_test() {
        // given
        User user = User.builder()
                .id(1)
                .build();
        Board board = Board.builder()
                .title("제목5")
                .content("내용5")
                .user(user)
                .build();
        // when
        boardJPARepository.save(board);

        // then
        System.out.println("board.getId() _test = " + board.getId());

    }

}