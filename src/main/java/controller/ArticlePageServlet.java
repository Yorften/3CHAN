package controller;

import java.io.IOException;

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
import model.enums.ArticleStatus;
import model.enums.CommentStatus;
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
    
    private ArticleService articleService = new ArticleService(articleRepository);
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

            int totalPages = commentService.getTotalPages(articleId, CommentStatus.APPROVED);

            if (pageNumber > totalPages) {
                errorPage(req, resp);
                return;
            }

            Article article = articleService.getArticleById(articleId).orElseGet(null);
            
            if(article == null) {
            	errorPage(req, resp);
                return;
            }
            
            List<Comment> approvedComments = commentService.getAllComments(articleId, pageNumber,
                    CommentStatus.APPROVED);

            int pendingCommentsCount = commentService.pendingCommentsCount(articleId);
                        
            req.setAttribute("article", article);
            req.setAttribute("approvedComments", approvedComments);
            req.setAttribute("articleId", articleId);
            req.setAttribute("currentPage", pageNumber);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("pendingCommentsCount", pendingCommentsCount);

        } else {
            errorPage(req, resp);
            return;
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/articlePage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String articleIdParam = req.getParameter("article_id");
        String pageNumberParam = req.getParameter("pageNumber");

        long articleId = Long.parseLong(articleIdParam);
        int pageNumber = Integer.parseInt(pageNumberParam);
        switch (action) {
            case "create_comment":
                Comment comment = new Comment();

                String authorIdParam = req.getParameter("authorId");

                long authorId = Long.parseLong(authorIdParam);
                Author author = new Author();
                author.setId(authorId);

                Article article = new Article();
                article.setId(articleId);

                String content = req.getParameter("content");
                comment.setContent(content);
                comment.setArticle(article);
                comment.setAuthor(author);

                commentService.saveComment(comment);

                break;
            case "delete_comment":

                String commentIdParam = req.getParameter("commentId");

                long commentId = Long.parseLong(commentIdParam);

                commentService.deleteComment(commentId);
                break;
                
            case "update_article":
                updateArticle(req, resp, articleId);
                break;

            case "delete_article":
        		articleService.deleteArticle(articleId);
                resp.sendRedirect("articles");
                return;
            default:
                break;
        }

        resp.sendRedirect("article?article_id=" + articleId + "&page=" + pageNumber);
    }

    protected void errorPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/404.jsp");
        dispatcher.forward(req, resp);
    }
    
    private void updateArticle(HttpServletRequest request, HttpServletResponse response, long articleId)
			throws ServletException, IOException {
    	
    	
    	
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		 
        Article article = articleService.getArticleById(articleId).orElseGet(null);

		article.setTitle(title);
		article.setContent(content);
		article.setArticleStatus(ArticleStatus.DRAFT);	 
		
		articleService.updateArticle(article);

	}
}
