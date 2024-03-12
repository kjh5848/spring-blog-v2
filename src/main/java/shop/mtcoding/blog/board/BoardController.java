package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class BoardController {

    private final BoardNativeRepository boardNativeRepository;


    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, String username,String title, String content) {
        boardNativeRepository.update(id,username,title,content);
        return "redirect:/board/{id}";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request){
        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board", board);
        return "board/update-form";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id) {
        boardNativeRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/board/save")
    public String save(String username, String title, String content) {
        boardNativeRepository.save(username, title, content);

        return "redirect:/";
    }


    @GetMapping("/")
    public String index(HttpServletRequest req) {
        List<Board> boardList = boardNativeRepository.findAll();
        req.setAttribute("boardList",boardList);
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "/board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board", board);
        return "/board/detail";
    }
}
