package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.utils.ApiUtil;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RestController
@AllArgsConstructor
public class BoardController {

    private final BoardSerivce boardSerivce;
    private final HttpSession session;

    @GetMapping("/")
    public ResponseEntity<?> main() {
        List<BoardResponse.MainDTO> respDTO = boardSerivce.목록조회();
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @GetMapping("/api/boards/{id}/detail")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DetailDTO respDTO = boardSerivce.글상세보기(id, sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @GetMapping("/api/boards/{id}")
    public ResponseEntity<?>  updateForm(@PathVariable Integer id) {
        BoardResponse.DTO respDTO = boardSerivce.글조회(id);
        System.out.println("respDTO = " + respDTO);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @PutMapping("/api/boards/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,@RequestBody BoardRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DTO respDTO =  boardSerivce.게시글수정(id, sessionUser.getId(), reqDTO);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @DeleteMapping( "/api/boards/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardSerivce.글삭제(id, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil(null));
    }

    @PostMapping("/api/boards")
    public ResponseEntity<?> save(@RequestBody BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DTO respDTO = boardSerivce.save(reqDTO,sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

}
