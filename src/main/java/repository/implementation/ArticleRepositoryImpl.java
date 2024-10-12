package repository.implementation;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Article;
import repository.interfaces.ArticleRepository;
import util.PersistenceUtil;

public class ArticleRepositoryImpl implements ArticleRepository {

	private static final Logger logger = LoggerFactory.getLogger(ArticleRepositoryImpl.class);

	private static final String LIST = "SELECT a FROM Article a LEFT JOIN FETCH a.comments";
	private static final String SEARCH = "SELECT DISTINCT a FROM Article a LEFT JOIN FETCH a.comments WHERE a.title LIKE :title";
	private static final String COUNT = "SELECT COUNT(a) FROM Article a";
	private static final String GET = "SELECT a FROM Article a JOIN FETCH a.comments WHERE a.id = :id";

	@Override
	public List<Article> getAllArticles(int page, int pageSize) {

		EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();

		try {
			TypedQuery<Article> query = entityManager.createQuery(LIST, Article.class);
			query.setFirstResult((page - 1) * pageSize);
			query.setMaxResults(pageSize);

			List<Article> articles = query.getResultList();

			return articles;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Optional<Article> getArticleById(long id) {

		EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();

		try {
			TypedQuery<Article> query = entityManager.createQuery(GET, Article.class);
			query.setParameter("id", id);
			Article article = query.getSingleResult();
			return Optional.ofNullable(article);
		} finally {
			entityManager.close();
		}
	}

	@Override
	public List<Article> searchArticleByTitle(String title) {
		EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
		try {
			TypedQuery<Article> query = entityManager.createQuery(SEARCH, Article.class);
			query.setParameter("title", "%" + title + "%");

			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

	@Override

	public void addArticle(Article article) {
		EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(article);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {

				transaction.rollback();
			}
			logger.error("Error Adding This Article", e);

		} finally {
			entityManager.close();
		}
	}

	@Override

	public void updateArticle(Article article) {
		EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			System.out.println(article.toString());
			entityManager.merge(article);
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			logger.error("Error Updating This Article", e);

		} finally {
			entityManager.close();
		}
	}

	@Override

	public void deleteArticle(Long id) {
		EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			Article article = entityManager.find(Article.class, id);
			if (article != null) {
				entityManager.remove(article);
			}
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			logger.error("Error Deleting This Article", e);
		}
	}

	@Override
	public long getTotalArticleCount() {
		EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();

		try {
			TypedQuery<Long> query = entityManager.createQuery(COUNT, Long.class);
			return query.getSingleResult();
		} finally {
			entityManager.close();
		}
	}

}
