package controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Article;
import repository.implementation.ArticleRepositoryImpl;
import repository.interfaces.ArticleRepository;
import service.ArticleService;

 
 

public class ArticleServlet extends HttpServlet {
	
	 private final ArticleRepository articleRepository = new ArticleRepositoryImpl();
	 private final ArticleService articleService = new ArticleService(articleRepository);
	
	@Override 
	
	protected void doGet(HttpServletRequest request ,HttpServletResponse response ) throws ServletException , IOException {
		String action = request.getParameter("action");
		
		if("get".equals(action)) {
			getArticleById(request,response);
		}else {
			listArticles(request,response);
		}
		
		
	}


	@Override
	
	protected void doPost(HttpServletRequest request ,HttpServletResponse response ) throws ServletException , IOException {
		String action = request.getParameter("action");
        HttpSession session = request.getSession();
		if("add".equals(action)) {
			addArticle(request,response);
		}else if ("update".equals(action)) {
			updateArticle(request,response);
		}else if ("delete".equals(action)) {
			deleteArticle(request,response);
		}else {
			 response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
		}
	}
	
	
	private void getArticleById(HttpServletRequest request, HttpServletResponse response)throws ServletException , IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Optional<Article> article = articleService.getArticleById(id);
		
	}

	private void listArticles(HttpServletRequest request, HttpServletResponse response)throws ServletException , IOException {
		// TODO Auto-generated method stub
		
	}

	private void deleteArticle(HttpServletRequest request, HttpServletResponse response)throws ServletException , IOException {
		// TODO Auto-generated method stub
		
	}

	private void updateArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException{
		// TODO Auto-generated method stub
		
	}

	private void addArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException{
		// TODO Auto-generated method stub
		
	}

}
