package com.example.blogapp.dtos;

import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;

@Data
public class CreateArticleDTO {

    @NonNull
    private String title;

    @NonNull
    private String body;

    @Nullable
    private String subtitle;
    private String slug;

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getSlug() {
        return slug;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
