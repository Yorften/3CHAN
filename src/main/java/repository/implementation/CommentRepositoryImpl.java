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
    private static final int ITEMS_PER_PAGE = 5;

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
    public List<Comment> getAll(long article_id, int pageNumber) {
        int startIndex = (pageNumber - 1) * ITEMS_PER_PAGE;

        EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Comment> typedQuery = entityManager
                    .createQuery("SELECT c FROM Comment c JOIN FETCH c.author WHERE c.article.id = :articleId", Comment.class);
            typedQuery.setParameter("articleId", article_id);
            typedQuery.setFirstResult(startIndex);
            typedQuery.setMaxResults(ITEMS_PER_PAGE);
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

    @Override
    public boolean hasNextPage(long article_id, int pageNumber) {
        EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Long> countQuery = entityManager
                    .createQuery("SELECT COUNT(c) FROM Comment c WHERE c.article.id = :articleId", Long.class);
            countQuery.setParameter("articleId", article_id);
            long totalComments = countQuery.getSingleResult();

            int totalPages = (int) Math.ceil((double) totalComments / ITEMS_PER_PAGE);

            return pageNumber < totalPages;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public int getTotalPages(long article_id) {
        EntityManager entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Long> countQuery = entityManager
                    .createQuery("SELECT COUNT(c) FROM Comment c WHERE c.article.id = :articleId", Long.class);
            countQuery.setParameter("articleId", article_id);
            long totalComments = countQuery.getSingleResult();

            return (int) Math.ceil((double) totalComments / ITEMS_PER_PAGE);

        } finally {
            entityManager.close();
        }
    }
}
