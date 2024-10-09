package service;

import java.util.List;
import java.util.Optional;

import model.Article;
import repository.interfaces.ArticleRepository;

public class ArticleService {

	private final ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	public List<Article> getAllArticles(int page, int pageSize) {
		return articleRepository.getAllArticles(page, pageSize);
	}

	public Optional<Article> getArticleById(Long id) {
		return articleRepository.getArticleById(id);
	}

	public List<Article> searchArticleByTitle(String title, int page, int pageSize) {

		return articleRepository.searchArticleByTitle(title, page, pageSize);
	}

	public void addArticle(Article article) {
		articleRepository.addArticle(article);
	}

	public void updateArticle(Article article) {
		articleRepository.updateArticle(article);
	}

	public void deleteArticle(Long id) {
		articleRepository.deleteArticle(id);
	}

	public long getTotalArticleCount() {
		return articleRepository.getTotalArticleCount();

	}

}
