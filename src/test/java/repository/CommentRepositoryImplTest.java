package repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Article;
import model.Author;
import model.Comment;
import model.enums.ArticleStatus;
import model.enums.Role;
import repository.implementation.CommentRepositoryImpl;
import util.PersistenceUtil;

@RunWith(MockitoJUnitRunner.class)
public class CommentRepositoryImplTest {
    private static final Logger logger = LoggerFactory.getLogger(CommentRepositoryImplTest.class);

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    private EntityManager entityManager;
    private CommentRepositoryImpl commentRepository;
    private Author author;
    private Article article;

    @BeforeClass
    public static void setUpBeforeClass() {
        System.setProperty("persistence.unit.name", "test_3CHAN_PU");
    }

    @Before
    public void setUp() {
        commentRepository = new CommentRepositoryImpl();

        author = new Author();
        author.setFirstName("first name test");
        author.setLastName("last name test");
        author.setEmail("email test");
        author.setBirthDay(LocalDate.of(2001, 1, 31));
        author.setRole(Role.CONTRIBUTOR);

        article = new Article();
        article.setTitle("test title");
        article.setContent("test content");
        article.setPublicationDate(LocalDateTime.now());
        article.setArticleStatus(ArticleStatus.PUBLISHED);
        article.setAuthor(author);

        entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(author);

        entityManager.persist(article);

        transaction.commit();
        entityManager.close();
    }

    @After
    public void tearDown() {

        entityManager = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.createQuery("DELETE FROM Comment").executeUpdate();
        entityManager.createQuery("DELETE FROM Article").executeUpdate();
        entityManager.createQuery("DELETE FROM Author").executeUpdate();

        transaction.commit();
        entityManager.close();
    }

    @Test
    public void addCommentTest() {

        Comment newComment = new Comment();
        newComment.setContent("This is a test comment");
        newComment.setCreationDate(LocalDateTime.now());
        newComment.setAuthor(author);
        newComment.setArticle(article);

        commentRepository.save(newComment);

        Comment insertedComment = commentRepository.get(newComment.getId()).get();

        logger.info("New comment : " + insertedComment.toString());

        assertNotNull(insertedComment.getId());
    }

    @Test
    public void updateCommentTest() {

        Comment newComment = new Comment();
        newComment.setContent("This is a test comment");
        newComment.setCreationDate(LocalDateTime.now());
        newComment.setAuthor(author);
        newComment.setArticle(article);

        commentRepository.save(newComment);

        newComment.setContent("Updated content");

        commentRepository.update(newComment);

        Comment updatedComment = commentRepository.get(newComment.getId()).get();

        logger.info("Updated comment : " + updatedComment.toString());

        assertEquals("Updated content", updatedComment.getContent());
    }

    @Test
    public void deleteCommentTest() {
        Comment newComment = new Comment();
        newComment.setContent("This is a test comment");
        newComment.setCreationDate(LocalDateTime.now());
        newComment.setAuthor(author);
        newComment.setArticle(article);

        commentRepository.save(newComment);

        Comment insertedComment = commentRepository.get(newComment.getId()).orElse(null);

        assertNotNull(insertedComment);

        commentRepository.delete(insertedComment.getId());

        Comment deletedComment = commentRepository.get(newComment.getId()).orElse(null);

        assertNull(deletedComment);

    }
}
