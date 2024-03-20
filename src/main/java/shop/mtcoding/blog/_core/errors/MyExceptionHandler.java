package shop.mtcoding.blog._core.errors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.errors.exception.*;
import shop.mtcoding.blog._core.utills.ApiUtil;

//RuntimeException이 터지면 해당 파일로 오류가 모인다.
@RestControllerAdvice
public class MyExceptionHandler {

    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> ex400(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(400,e.getMessage());// http body -> 구성한 객체
        return new ResponseEntity<>(apiUtil, HttpStatus.BAD_REQUEST); // http body, header
    }

    @ExceptionHandler(Exception401.class)
    public ResponseEntity<?> ex401(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(401,e.getMessage());// http body -> 구성한 객체
        return new ResponseEntity<>(apiUtil, HttpStatus.UNAUTHORIZED ); // http body, header
    }

    @ExceptionHandler(Exception403.class)
    public ResponseEntity<?> ex403(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(403,e.getMessage());// http body -> 구성한 객체
        return new ResponseEntity<>(apiUtil, HttpStatus.FORBIDDEN ); // http body, header
    }


    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> ex404(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(404,e.getMessage());// http body -> 구성한 객체
        return new ResponseEntity<>(apiUtil, HttpStatus.NOT_FOUND); // http body, header
    }

    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> ex500(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(500,e.getMessage());// http body -> 구성한 객체
        return new ResponseEntity<>(apiUtil, HttpStatus.INTERNAL_SERVER_ERROR);
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