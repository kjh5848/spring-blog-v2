package shop.mtcoding.blog._core.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardRepository;
import shop.mtcoding.blog.user.User;

public class UpdateIntercepter implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle............");
        HttpSession session = request.getSession();

        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = new Board();

        if(sessionUser == null){
            throw new Exception403("권한이 없습니다.");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("preHandle............");
        HttpSession session = request.getSession();

        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = (Board) request.getAttribute("board");

        if(sessionUser.getId() != board.getUser().getId()){
            throw new Exception403("권한이 없습니다.");
        }
    }
}
