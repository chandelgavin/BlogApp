package com.example.blogapp.articles;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticlesController {

    @GetMapping
    String getArticles(){
        return "articles";
    }

}
