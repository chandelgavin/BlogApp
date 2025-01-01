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
}
