package service;

import repository.interfaces.CommentRepository;

import model.Comment;
import model.enums.CommentStatus;

import java.util.List;
import java.util.Optional;


public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Optional<Comment> getComment(long id) {
        return commentRepository.get(id);
    }

    public List<Comment> getAllComments(long article_id, int pageNumber, CommentStatus commentStatus) {
        return commentRepository.getAll(article_id, pageNumber, commentStatus);
    }

    public boolean hasNextPage(long article_id, int pageNumber, CommentStatus commentStatus) {
        return commentRepository.hasNextPage(article_id, pageNumber, commentStatus);
    }

    public int getTotalPages(long article_id, CommentStatus commentStatus) {
        return commentRepository.getTotalPages(article_id, commentStatus);
    }

    public int pendingCommentsCount(long article_id) {
        return commentRepository.pendingCommentsCount(article_id);
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void updateComment(Comment comment) {
        commentRepository.update(comment);
    }

    public void deleteComment(long id) {
        commentRepository.delete(id);
    }

}
