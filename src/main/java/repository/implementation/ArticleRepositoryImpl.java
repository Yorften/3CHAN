package repository.implementation;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Article;
import repository.interfaces.ArticleRepository;
import util.PersistenceUtil;

public class ArticleRepositoryImpl implements ArticleRepository {

	private static final Logger logger = LoggerFactory.getLogger(ArticleRepositoryImpl.class);

	@Override
	public List<Article> getAllArticles(int page, int pageSize) {

		EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();

		try {
			TypedQuery<Article> query = entityManager.createQuery("SELECT a FROM Article a", Article.class);
			query.setFirstResult((page - 1) * pageSize);
			query.setMaxResults(pageSize);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Optional<Article> getArticleById(long id) {

		EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();

		try {
			Article article = entityManager.find(Article.class, id);
			return Optional.ofNullable(article);
		} finally {
			entityManager.close();
		}
	}

	@Override

	public List<Article> searchArticleByTitle(String title, int page, int pageSize) {
		EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
		try {
			TypedQuery<Article> query = entityManager.createQuery("SELECT a FROM Article a WHERE LOWER (:title)",
					Article.class);
			query.setParameter("title", "%" + title + "%");
			query.setFirstResult((page - 1) * pageSize);
			query.setMaxResults(pageSize);
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
		}catch (Exception e){
			if(transaction !=null && transaction.isActive()) {
				
				transaction.rollback();
			}
			logger.error("Error Adding This Article",e);
			
			
		}finally {
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
			entityManager.merge(article);
			transaction.commit();
			
		}catch(Exception e){
			if(transaction !=null && transaction.isActive()) {
				transaction.rollback();
			}
			logger.error("Error Updating This Article",e);
			
		}finally {
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
			if(article != null) {
				entityManager.remove(article);
			}
			transaction.commit();
			
			
		}catch(Exception e) {
			if(transaction !=null && transaction.isActive()) {
				transaction.rollback();
			}
			logger.error("Error Deleting This Article",e);
		}
	}

	@Override
	public long getTotalArticleCount() {
		 EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			 TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(a) FROM Article a",Long.class);
			 return query.getSingleResult();
		 }finally {
			 entityManager.close();
		 }
	}

}
