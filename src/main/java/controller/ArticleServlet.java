package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Article;
import model.Author;
import model.enums.ArticleStatus;
import repository.implementation.ArticleRepositoryImpl;
import repository.interfaces.ArticleRepository;
import service.ArticleService;

public class ArticleServlet extends HttpServlet {

	private final ArticleRepository articleRepository = new ArticleRepositoryImpl();
	private final ArticleService articleService = new ArticleService(articleRepository);

	@Override

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		listArticles(request, response);

	}

	@Override

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		if ("add".equals(action)) {
			addArticle(request, response);
		} else if ("update".equals(action)) {
			updateArticle(request, response);
		} else if ("delete".equals(action)) {
			deleteArticle(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
		}
	}

	private void listArticles(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
		int pageSize = 10;

		List<Article> articles = articleService.getAllArticles(page, pageSize);
		Long totalArticles = articleService.getTotalArticleCount();
		int totalPages = (int) Math.ceil((double) totalArticles / pageSize);

		request.setAttribute("articles", articles);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPages", totalPages);

		request.getRequestDispatcher("views/article.jsp").forward(request, response);
	}

	private void addArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String creationDateParam = request.getParameter("creation_date");
		String publicationDateParam = request.getParameter("publication_date");
		String articleStatutParam = request.getParameter("article_statut");
		String authorIdParam = request.getParameter("auhtor_id");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		LocalDateTime creationDate = creationDateParam != null ? LocalDateTime.parse(creationDateParam, formatter)
				: LocalDateTime.now();

		LocalDateTime publicationDate = publicationDateParam != null
				? LocalDateTime.parse(publicationDateParam, formatter)
				: null;
		ArticleStatus statut = articleStatutParam != null ? ArticleStatus.valueOf(articleStatutParam.toUpperCase())
				: ArticleStatus.DRAFT;

		Long authorId = authorIdParam != null ? Long.parseLong(authorIdParam) : null;
		Author author = new Author();
		author.setId(authorId);

		Article article = new Article();
		article.setTitle(title);
		article.setContent(content);
		article.setCreationDate(creationDate);
		article.setPublicationDate(publicationDate);
		article.setArticleStatus(statut);
		article.setAuthor(author);
		articleService.updateArticle(article);

		response.sendRedirect("article?action=list&page=1");

	}

	private void updateArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String creationDateParam = request.getParameter("creation_date");
		String publicationDateParam = request.getParameter("publication_date");
		String articleStatutParam = request.getParameter("article_statut");
		String authorIdParam = request.getParameter("author_id");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		LocalDateTime creationDate = creationDateParam != null ? LocalDateTime.parse(creationDateParam, formatter)
				: LocalDateTime.now();

		LocalDateTime publicationDate = publicationDateParam != null
				? LocalDateTime.parse(publicationDateParam, formatter)
				: null;

		ArticleStatus statut = articleStatutParam != null ? ArticleStatus.valueOf(articleStatutParam.toUpperCase())
				: ArticleStatus.DRAFT;
		
		Long authorId = authorIdParam != null ? Long.parseLong(authorIdParam) : null;
		Author author = new Author();
		author.setId(authorId);
		
		Article article = new Article();
		
		article.setTitle(title);
		article.setContent(content);
		article.setCreationDate(creationDate);
		article.setPublicationDate(publicationDate);
		article.setArticleStatus(statut);
		article.setAuthor(author);
		
		articleService.updateArticle(article);
		
		response.sendRedirect("article?action=list&page=1");
		
		

	}
	

	private void deleteArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 Long id = Long.parseLong(request.getParameter("id"));
		 articleService.deleteArticle(id);
		 
		 response.sendRedirect("article?action=list&page=1");

	}

}
