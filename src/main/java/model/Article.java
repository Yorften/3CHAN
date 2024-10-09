package model;

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

import org.hibernate.annotations.ColumnDefault;

import model.enums.ArticleStatus;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Title should not be null")
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull(message = "Article content should not be null")
    @Column(name = "content", nullable = false)
    private String content;

    @NotNull(message = "Date should not be null")
    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @Column(name = "publication_date")
    private LocalDateTime publicationDate;

    @ColumnDefault("'DRAFT'")
    @NotNull(message = "Article status is null")
    @Enumerated(EnumType.STRING)
    @Column(name = "article_status")
    private ArticleStatus articleStatus;

    @JoinColumn(name = "author_id", nullable = false)
    @NotNull(message = "Author is needed")
    @ManyToOne
    private Author author;

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                ", publicationDate=" + publicationDate +
                ", articleStatus=" + articleStatus +
                ", author=" + author +
                '}';
    }

    public long getId() {
      return this.id;
    }
    public void setId(long value) {
      this.id = value;
    }

    public String getTitle() {
      return this.title;
    }
    public void setTitle(String value) {
      this.title = value;
    }

    public String getContent() {
      return this.content;
    }
    public void setContent(String value) {
      this.content = value;
    }

    public LocalDateTime getCreationDate() {
      return this.creationDate;
    }
    public void setCreationDate(LocalDateTime value) {
      this.creationDate = value;
    }

    public LocalDateTime getPublicationDate() {
      return this.publicationDate;
    }
    public void setPublicationDate(LocalDateTime value) {
      this.publicationDate = value;
    }

    public ArticleStatus getArticleStatus() {
      return this.articleStatus;
    }
    public void setArticleStatus(ArticleStatus value) {
      this.articleStatus = value;
    }

    public Author getAuthor() {
      return this.author;
    }
    public void setAuthor(Author value) {
      this.author = value;
    }
}
