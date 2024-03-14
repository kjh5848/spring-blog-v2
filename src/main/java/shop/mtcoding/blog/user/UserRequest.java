package shop.mtcoding.blog.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserRequest {

    @AllArgsConstructor
    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }

    @Data
    public static class SaveDTO {
        private String username;
        private String password;
        private String email;

        public User toEntity() {
            return new User(username, password, email);
        }
    }
}
