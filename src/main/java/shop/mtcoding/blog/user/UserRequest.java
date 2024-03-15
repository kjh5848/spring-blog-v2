package shop.mtcoding.blog.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserRequest {

    @Data
    public static class UpdateDTO {
        private String password;
        private String email;
    }


    @AllArgsConstructor
    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }

    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;

        public User toEntity() {
            return new User(username, password, email);
        }
    }
}
