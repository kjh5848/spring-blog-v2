package shop.mtcoding.blog.user;

import lombok.Data;

public class UserResponse {

    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;

        public JoinDTO(User user) {
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.email = user.getEmail();
        }
    }

    @Data
    public static class DTO {
        private Integer id;
        private String username;
        private String email;

        public DTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
        }
    }
}
