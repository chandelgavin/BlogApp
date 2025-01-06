package com.example.blogapp.articles;

import com.example.blogapp.dtos.CreateArticleDTO;
import com.example.blogapp.dtos.UpdateArticleDTO;
import com.example.blogapp.users.UserRepository;
import com.example.blogapp.users.UserService;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;
    private UserRepository userRepository;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public Iterable<ArticleEntity> getAllArticles(){
        return articleRepository.findAll();
    }

    public ArticleEntity getArticleBySlug(String slug){
        var article = articleRepository.findBySlug(slug);
        if(article==null){
            throw new ArticleNotFoundException(slug);
        }
        return article;
    }

    public ArticleEntity createArticle(CreateArticleDTO articleDTO, Long authorId){
        var author = userRepository.findById(authorId);
        if(author==null){
            throw new UserService.UserNotFoundException(authorId);
        }

        return articleRepository.save(ArticleEntity.builder().
                title(articleDTO.getTitle()).
                slug(articleDTO.getTitle().toLowerCase().replaceAll("\\s", "-")).
                subtitle(articleDTO.getSubtitle()).
                body(articleDTO.getBody()).
                build());
    }

    public ArticleEntity updateArticle(Long articleId, UpdateArticleDTO updatedArticle){
        var article = articleRepository.findById(articleId).orElseThrow(()->new ArticleNotFoundException(articleId));
            article.setTitle(updatedArticle.getTitle());
            article.setSlug(updatedArticle.getTitle().toLowerCase().replaceAll("\\s","-"));
            article.setTitle(updatedArticle.getBody());

        if(updatedArticle.getSubtitle()!=null){
            article.setTitle(updatedArticle.getSubtitle());
        }
        return articleRepository.save(article);
    }

    static class ArticleNotFoundException extends IllegalArgumentException{
        public ArticleNotFoundException(String slug) {
            super("Article with slug "+slug+" not found.");
        }

        public ArticleNotFoundException(Long articleId) {
            super("Article with id "+articleId+" not found.");
        }
    }
}
