package repository.interfaces;

import java.util.List;
import java.util.Optional;

import model.Comment;
import model.enums.CommentStatus;

public interface CommentRepository {

    Optional<Comment> get(long id);

    List<Comment> getAll(long article_id, int pageNumber, CommentStatus commentStatus);

    boolean hasNextPage(long article_id, int pageNumber, CommentStatus commentStatus);

    int getTotalPages(long article_id, CommentStatus commentStatus);

    int pendingCommentsCount(long article_id);

    void save(Comment comment);

    void update(Comment comment);

    void delete(long id);

}
