package com.example.blogapp.comments;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity(name = "comments")
@Getter
@Setter
public class CommentEntity {
    @Id
    private int id;
}
