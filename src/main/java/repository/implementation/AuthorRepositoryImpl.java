package repository.implementation;

import model.Author;

import org.hibernate.annotations.QueryHints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.interfaces.AuthorRepository;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {

    private static final Logger logger = LoggerFactory.getLogger(AuthorRepositoryImpl.class);


    @Override
    public void addAuthor(Author author) {
        EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(author);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Error Adding Author", e);
        } finally {
            entityManager.close();
        }
    }


    @Override
    public void updateAuthor(Author author) {
        EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(author);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Error Updating Author", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteAuthor(Long authorId) {
        EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Author author = entityManager.find(Author.class, authorId);
            if (author != null) {
                entityManager.remove(author);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Error Deleting Author", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Author> getAllAuthors() {
        EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        List<Author> authors = null;
        try {
        	authors = entityManager.createQuery("SELECT distinct a FROM Author a LEFT JOIN FETCH a.comments", Author.class).setHint(QueryHints.PASS_DISTINCT_THROUGH, false).getResultList();
        	authors = entityManager.createQuery("SELECT distinct a FROM Author a LEFT JOIN FETCH a.articles WHERE a in :authors", Author.class).setParameter("authors", authors).setHint(QueryHints.PASS_DISTINCT_THROUGH, false).getResultList();

            logger.info("Authors retrieved: " + authors);
        } catch (Exception e) {
            logger.error("Error Retrieving Authors", e);
        } finally {
            entityManager.close();
        }
        return authors;
    }


    @Override
    public Author getAuthorById(Long authorId) {
        EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        Author author = null;
        try {
            author = entityManager.find(Author.class, authorId);
            if (author != null) {

                author.getComments().size();
                author.getArticles().size();
            }
            logger.info("Author retrieved with ID: " + authorId);
        } catch (Exception e) {
            logger.error("Error Retrieving Author with ID: " + authorId, e);
        } finally {
            entityManager.close();
        }
        return author;
    }



}
