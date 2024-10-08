package model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import model.enums.CommentStatus;


@Entity
@Table(name = "comment")
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Content should not be null")
    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @NotNull(message = "Date should not be null")
    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "comment_status", nullable = true)
    private CommentStatus commentStatus;

    @JoinColumn(name = "author_id", nullable = false)
    @NotNull(message = "Author is needed")
    @ManyToOne
    private Author author;

    @JoinColumn(name = "article_id", nullable = false)
    @NotNull(message = "Article is needed")
    @ManyToOne
    private Article article;

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                ", commentStatus=" + commentStatus +
                ", author=" + author +
                '}';
    }


    public Article getArticle() {
      return this.article;
    }
    public void setArticle(Article value) {
      this.article = value;
    }

    public Author getAuthor() {
      return this.author;
    }
    public void setAuthor(Author value) {
      this.author = value;
    }

    public CommentStatus getCommentStatus() {
      return this.commentStatus;
    }
    public void setCommentStatus(CommentStatus value) {
      this.commentStatus = value;
    }

    public LocalDateTime getCreationDate() {
      return this.creationDate;
    }
    public void setCreationDate(LocalDateTime value) {
      this.creationDate = value;
    }

    public String getContent() {
      return this.content;
    }
    public void setContent(String value) {
      this.content = value;
    }

    public long getId() {
      return this.id;
    }
    public void setId(long value) {
      this.id = value;
    }
}
