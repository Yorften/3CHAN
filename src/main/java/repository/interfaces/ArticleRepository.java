package repository.interfaces;

import java.util.List;
import java.util.Optional;

import model.Article;

public interface ArticleRepository {
	
	List<Article> getAllArticles(int page , int pageSize);
	
	Optional<Article> getArticleById(long id);
	
	List<Article> searchArticleByTitle(String title ,int page , int pageSize);
	
	void addArticle(Article article);
	
	void updateArticle(Article article);
	
	void deleteArticle(Long id);
	
	long getTotalArticleCount();
	
	

}
