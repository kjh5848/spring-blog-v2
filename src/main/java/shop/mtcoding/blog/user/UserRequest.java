package shop.mtcoding.blog.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserRequest {

    @Data
    public static class UpdateDTO {
        private String password;

        //DTO를 클라이언트로 부터 받아서 PC에 전달하기 위해 사용
        public User toEntity(User user) {
            return User.builder()
                    .password(password)
                    .build();
        }
    }

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
