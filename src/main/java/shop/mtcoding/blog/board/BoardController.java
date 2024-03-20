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

    //TODO: 글조회 API 필요 -> @GetMapping("/")
    //TODO: 글목록조회 API 필요 -> @GetMapping("/api/boards/{id}/detail")
    //TODO: 글상세보기 API 필요 -> @GetMapping("/api/boards/{id}")

    @PutMapping("/api/boards/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody BoardRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        return ResponseEntity.ok(new ApiUtil(boardService.updateById(id, sessionUser.getId(), reqDTO)));
    }

    @DeleteMapping( "/api/boards/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        return ResponseEntity.ok(new ApiUtil(boardService.글삭제(id, sessionUser.getId())));
    }

    @PostMapping("/api/boards")
    public ResponseEntity<?> save(@RequestBody BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        return ResponseEntity.ok(new ApiUtil(boardService.save(reqDTO, sessionUser)));
    }

}
