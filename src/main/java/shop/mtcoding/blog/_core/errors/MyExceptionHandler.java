package shop.mtcoding.blog._core.errors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import shop.mtcoding.blog._core.errors.exception.*;

//RuntimeException이 터지면 해당 파일로 오류가 모인다.
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception400.class)
    public String ex400(RuntimeException e, HttpServletRequest req) {
        req.setAttribute("msg",e.getMessage());
        return "err/400";
    }

    @ExceptionHandler(Exception401.class)
    public String ex401(RuntimeException e, HttpServletRequest req) {
        req.setAttribute("msg",e.getMessage());
        return "err/401";
    }

    @ExceptionHandler(Exception403.class)
    public String ex403(RuntimeException e, HttpServletRequest req) {
        req.setAttribute("msg",e.getMessage());
        return "err/403";
    }

    @ExceptionHandler(Exception404.class)
    public String ex404(RuntimeException e, HttpServletRequest req) {
        req.setAttribute("msg",e.getMessage());
        return "err/404";
    }

    @ExceptionHandler(Exception500.class)
    public String ex500(RuntimeException e, HttpServletRequest req) {
        req.setAttribute("msg",e.getMessage());
        return "err/500";
    }

    //널포인트 익셉션
    //내가 못잡은 것들은 다 언노운으로 온다.
//    //실무에서는 이게 있어야 한다.
//    @ExceptionHandler(Exception.class)
//    public String exUnknown(RuntimeException e, HttpServletRequest req) {
//        req.setAttribute("msg",e.getMessage());
////     DB에러 로그도 남기고
////    관리자 문자 날려주고
////    이메일도 보내고
//        return "err/Unknown";
//    }
}