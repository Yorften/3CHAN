package controller;

import model.Author;
import repository.implementation.AuthorRepositoryImpl;
import repository.interfaces.AuthorRepository;
import service.AuthorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class AuthorServlet extends HttpServlet {

	private AuthorRepository authorRepository = new AuthorRepositoryImpl();
    private AuthorService authorService = new AuthorService(authorRepository);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Author> authors = authorService.getAllAuthors();
        request.setAttribute("authors", authors);


        request.getRequestDispatcher("/views/author.jsp").forward(request, response);
    }
}
