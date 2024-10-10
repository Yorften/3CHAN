package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Article;
import model.Author;
import model.Comment;
import repository.implementation.ArticleRepositoryImpl;
import repository.implementation.CommentRepositoryImpl;
import repository.interfaces.ArticleRepository;
import repository.interfaces.CommentRepository;
import service.ArticleService;
import service.CommentService;

public class ArticlePageServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ArticlePageServlet.class);

    private ArticleRepository articleRepository = new ArticleRepositoryImpl();
    private CommentRepository commentRepository = new CommentRepositoryImpl();

    // private ArticleService articleService = new
    // ArticleService(articleRepository);
    private CommentService commentService = new CommentService(commentRepository);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String articleIdParam = req.getParameter("article_id");
        String pageNumberParam = req.getParameter("page");
        long articleId;
        int pageNumber;

        if (articleIdParam != null && !articleIdParam.trim().isEmpty() && pageNumberParam != null
                && !pageNumberParam.trim().isEmpty()) {
            try {
                articleId = Long.parseLong(articleIdParam);
                pageNumber = Integer.parseInt(pageNumberParam);
                if (articleId < 0 || pageNumber <= 0) {
                    logger.error("Params articleId or pageNumber cannot be negative");
                    throw new IllegalArgumentException("Params articleId or pageNumber cannot be negative.");
                }
            } catch (NumberFormatException e) {
                logger.error("Invalid article ID format", e);
                throw new IllegalArgumentException("Invalid article ID format.");
            }

            int totalPages = commentService.getTotalPages(articleId);

            if (pageNumber > totalPages) {
                errorPage(req, resp);
            }

            // To be deleted
            Author author = new Author();
            author.setFirstName("Joe");
            author.setLastName("Doe");

            Article article = new Article();
            article.setTitle("Test Article");
            article.setAuthor(author);
            article.setPublicationDate(LocalDateTime.now());
            article.setContent(
                    "Lorem ipsum dolor sit amet consectetur adipisicing elit. Doloremque maiores iure officiis eum placeat distinctio, commodi laudantium dignissimos architecto debitis facere fuga assumenda suscipit quo nobis consequatur veniam error a repudiandae delectus voluptatum ducimus eveniet tempore dolores. Tempore nemo voluptas fuga, debitis temporibus hic quasi porro maiores nam molestias, impedit totam unde at consectetur ad eum voluptates quidem commodi distinctio perferendis quia facilis! Facere tempore atque, rem odit eligendi exercitationem vero impedit a fugiat. Quae fugit ut expedita laudantium ab ratione officia officiis soluta natus, eum ad. Exercitationem eveniet quam nesciunt illo id iste pariatur, fugit tenetur! Assumenda, eos nisi?");

            List<Comment> comments = commentService.getAllComments(articleId, pageNumber);

            req.setAttribute("article", article);
            req.setAttribute("comments", comments);

        } else {
            errorPage(req, resp);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/articlePage.jsp");
        dispatcher.forward(req, resp);
    }

    protected void errorPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/404.jsp");
        dispatcher.forward(req, resp);
    }
}
