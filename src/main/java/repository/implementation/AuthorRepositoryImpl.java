package repository.implementation;

import model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.interfaces.AuthorRepository;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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


}
