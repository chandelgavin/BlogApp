package com.example.blogapp.users;

import com.example.blogapp.articles.ArticleEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Entity(name = "users")
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String username;

    @Column(nullable = false)
    @NonNull
    private String email;

    @Column(nullable = false)
    @NonNull
    private String password;

    @Column(nullable = true)
    @Nullable
    private String bio;

    @Column(nullable = true)
    @Nullable
    private String image;

    @NonNull
    public String getPassword() {
        return password;
    }

    public static UserEntityBuilder builder() {
        return new UserEntityBuilder();
    }

    public static class UserEntityBuilder {
        private String username;
        private String password;
        private String email;

        public UserEntity.UserEntityBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserEntity.UserEntityBuilder password(String password) {
            this.email = password;
            return this;
        }

        public UserEntity.UserEntityBuilder email(String email) {
            this.email = email;
            return this;
        }


        public UserEntity build() {
            UserEntity article = new UserEntity();
            article.username = this.username;
            article.password = this.password;
            article.email = this.email;

            return article;
        }
    }
}
