package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.utills.ApiUtil;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final HttpSession session;

    @GetMapping("/")
    public ResponseEntity<?> main(@RequestParam(defaultValue = "0") int page) {
        List<BoardResponse.MainDTO> respDTO = boardService.글목록조회(page);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @GetMapping("/api/boards/{id}/detail")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DetailDTO respDTO = boardService.글상세보기(id, sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @GetMapping("/api/boards/{id}")
    public ResponseEntity<?>  updateForm(@PathVariable Integer id) {
        BoardResponse.DTO respDTO = boardService.글조회(id);
        System.out.println("respDTO = " + respDTO);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @PutMapping("/api/boards/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody BoardRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DTO respDTO =  boardService.updateById(id, sessionUser.getId(), reqDTO);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @DeleteMapping( "/api/boards/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글삭제(id, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil(null));
    }

    @PostMapping("/api/boards")
    public ResponseEntity<?> save(@RequestBody BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DTO  respDTO= boardService.save(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

}
