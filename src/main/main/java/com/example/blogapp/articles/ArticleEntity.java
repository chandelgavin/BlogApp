package com.example.blogapp.articles;

import com.example.blogapp.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;

import java.util.Date;

@Entity(name = "articles")
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NonNull
    private String title;

    private String subtitle;

    @NonNull
    @Column(unique = true)
    private String slug;

    @NonNull
    private String body;

    @CreatedDate
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private UserEntity author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @NonNull
    public String getSlug() {
        return slug;
    }

    public void setSlug(@NonNull String slug) {
        this.slug = slug;
    }

    @NonNull
    public String getBody() {
        return body;
    }

    public void setBody(@NonNull String body) {
        this.body = body;
    }

    public static ArticleEntityBuilder builder() {
        return new ArticleEntityBuilder();
    }

    public static class ArticleEntityBuilder {
        private String title;
        private String slug;
        private String subtitle;
        private String body;


        public ArticleEntityBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ArticleEntityBuilder slug(String slug) {
            this.slug = slug;
            return this;
        }

        public ArticleEntityBuilder subtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public ArticleEntityBuilder body(String body) {
            this.body = body;
            return this;
        }

        public ArticleEntity build() {
            ArticleEntity article = new ArticleEntity();
            article.title = this.title;
            article.slug = this.slug;
            article.subtitle = this.subtitle;
            article.body = this.body;
            return article;
        }
    }
}
