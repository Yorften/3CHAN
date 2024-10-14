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

import model.Comment;
import model.enums.CommentStatus;
import repository.implementation.CommentRepositoryImpl;
import repository.interfaces.CommentRepository;
import service.CommentService;

public class PendingCommentServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ArticlePageServlet.class);

    private CommentRepository commentRepository = new CommentRepositoryImpl();

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

            int totalPages = commentService.getTotalPages(articleId, null);

            if (pageNumber > totalPages) {
                errorPage(req, resp);
                return;
            }

            List<Comment> pendingComments = commentService.getAllComments(articleId, pageNumber, null);

            req.setAttribute("pendingComments", pendingComments);
            req.setAttribute("articleId", articleId);
            req.setAttribute("currentPage", pageNumber);
            req.setAttribute("totalPages", totalPages);

        } else {
            errorPage(req, resp);
            return;
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/pendingComments.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commentIdParam = req.getParameter("commentId");
        String articleIdParam = req.getParameter("articleId");
        String pageNumberParam = req.getParameter("pageNumber");
        long commentId;
        long articleId;
        long pageNumber;

        if (commentIdParam != null && !commentIdParam.trim().isEmpty() && articleIdParam != null
                && !articleIdParam.trim().isEmpty() && pageNumberParam != null && !pageNumberParam.trim().isEmpty()) {
            try {
                commentId = Long.parseLong(commentIdParam);
                articleId = Long.parseLong(articleIdParam);
                pageNumber = Long.parseLong(pageNumberParam);
                if (commentId < 0 || articleId < 0 || pageNumber < 0) {
                    logger.error("Params articleId or pageNumber or commentId cannot be negative");
                    throw new IllegalArgumentException(
                            "Params articleId or pageNumber or commentId cannot be negative.");
                }
            } catch (NumberFormatException e) {
                logger.error("Invalid comment ID format", e);
                throw new IllegalArgumentException("Invalid comment ID format.");
            }

            String action = req.getParameter("action");
            Comment comment;
            switch (action) {
                case "approve":
                    comment = commentService.getComment(commentId).orElse(null);
                    comment.setCommentStatus(CommentStatus.APPROVED);
                    commentService.updateComment(comment);
                    req.setAttribute("message", "Commend approved successfully!");
                    break;
                case "reject":
                    comment = commentService.getComment(commentId).orElse(null);
                    comment.setCommentStatus(CommentStatus.REJECTED);
                    commentService.updateComment(comment);
                    req.setAttribute("message", "Commend rejected successfully!");
                    break;
                default:
                    break;
            }

        } else {
            logger.error("Invalid comment ID format");
            throw new IllegalArgumentException("Invalid comment ID format.");
        }

        resp.sendRedirect("pending?article_id=" + articleId + "&page=" + pageNumber);
    }

    protected void errorPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/404.jsp");
        dispatcher.forward(req, resp);
    }
}
