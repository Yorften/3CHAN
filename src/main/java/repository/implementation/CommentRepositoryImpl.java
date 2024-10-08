package repository.implementation;

import repository.interfaces.CommentRepository;
import util.PersistenceUtil;
import model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class CommentRepositoryImpl implements CommentRepository {
    private static final Logger logger = LoggerFactory.getLogger(CommentRepositoryImpl.class);

    @Override
    public Optional<Comment> get(long id) {
        EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(Comment.class, id));
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Comment> getAll(long article_id) {
        EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Comment> typedQuery = entityManager.createQuery("SELECT c FROM Comment c WHERE c.article_id = :articleId", Comment.class);
            typedQuery.setParameter("articleId", article_id);
            return typedQuery.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void save(Comment comment) {
        EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(comment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Error creating comment: ", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Comment comment) {
        EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(comment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Error updating comment: ", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(long id) {
        EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Comment comment = entityManager.find(Comment.class, id);
            if (comment != null) {
                entityManager.remove(comment);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Error deleting comment: ", e);
        } finally {
            entityManager.close();
        }
    }
}
