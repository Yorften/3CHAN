package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "author_comment")
public class AuthorComment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @JoinColumn(name = "author_id", nullable = false)
  @ManyToOne
  private Author author;

  @JoinColumn(name = "comment_id", nullable = false)
  @ManyToOne
  private Comment comment;

  public Author getAuthor() {
    return this.author;
  }

  public void setAuthor(Author value) {
    this.author = value;
  }

  public Comment getComment() {
    return this.comment;
  }

  public void setComment(Comment value) {
    this.comment = value;
  }
}
