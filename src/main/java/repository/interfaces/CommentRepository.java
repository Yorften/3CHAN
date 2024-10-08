package repository.interfaces;

import java.util.List;
import java.util.Optional;

import model.Comment;

public interface CommentRepository {

    Optional<Comment> get(long id);

    List<Comment> getAll(long article_id);

    void save(Comment comment);

    void update(Comment comment);

    void delete(long id);

}
