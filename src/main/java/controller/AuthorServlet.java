package controller;

import model.Author;
import model.enums.Role;
import repository.implementation.AuthorRepositoryImpl;
import repository.interfaces.AuthorRepository;
import service.AuthorService;
import util.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AuthorServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(AuthorServlet.class);

    private AuthorRepository authorRepository = new AuthorRepositoryImpl();
    private AuthorService authorService = new AuthorService(authorRepository);

    /*
     * public void displayAuteur(HttpServletRequest request, HttpServletResponse
     * response) throws ServletException, IOException {
     * List<Author> authors = authorService.getAllAuthors();
     * request.setAttribute("authors", authors);
     * request.getRequestDispatcher("/views/author.jsp").forward(request, response);
     * }
     */

    public void displayAuteur(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pageNumber = 1; // Numéro de page par défaut
        int pageSize = 5; // Nombre d'auteurs par page

        String page = request.getParameter("page");
        if (page != null && !page.isEmpty()) {
            pageNumber = Integer.parseInt(page);
        }

        List<Author> authors = authorService.getAllAuthors(pageNumber, pageSize);
        request.setAttribute("authors", authors);

        // Ajoutez également le nombre total d'auteurs pour la pagination
        long totalAuthors = authorService.countAuthors(); // Créez une méthode pour compter les auteurs
        request.setAttribute("totalAuthors", totalAuthors);
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("pageSize", pageSize);

        request.getRequestDispatcher("/views/author.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        displayAuteur(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action != null && action.equals("add")) {
            addAuthor(request, response);
        } else if (action.equals("delete")) {
            deleteAuthor(request, response);

        } else if (action != null && action.equals("update")) {
            updateAuthor(request, response);
        }
    }

    public void addAuthor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String birthDateString = request.getParameter("dateOfBirth");
        String roleString = request.getParameter("role");

        LocalDate birthDate = LocalDate.parse(birthDateString);
        Role role = Role.valueOf(roleString.toUpperCase());

        Author newAuthor = new Author();
        newAuthor.setFirstName(firstName);
        newAuthor.setLastName(lastName);
        newAuthor.setEmail(email);
        newAuthor.setBirthDay(birthDate);
        newAuthor.setRole(role);

        List<String> errors = new ArrayList<>();
        Validator validator = new Validator();
        validator.validateAuthor(newAuthor, errors);

        if (errors.isEmpty()) {
            authorService.addAuthor(newAuthor);
            request.setAttribute("successMessage", "Author added successfully!");
        } else {
            request.setAttribute("errorMessages", errors);
        }

        displayAuteur(request, response);
    }

    public void deleteAuthor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String authorIdString = request.getParameter("id");
        long authorId = Integer.parseInt(authorIdString);

        authorService.deleteAuthor(authorId);

        request.setAttribute("successMessage", "Author deleted successfully!");

        displayAuteur(request, response);
    }

    public void updateAuthor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idString = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String birthDateString = request.getParameter("dateOfBirth");
        String roleString = request.getParameter("role");

        Long id = Long.parseLong(idString);
        LocalDate birthDate = LocalDate.parse(birthDateString);
        Role role = Role.valueOf(roleString.toUpperCase());

        Author updateAuthor = authorService.getAuthorById(id);

        logger.info(updateAuthor.toString());

        updateAuthor.setFirstName(firstName);
        updateAuthor.setLastName(lastName);
        updateAuthor.setEmail(email);
        updateAuthor.setBirthDay(birthDate);
        updateAuthor.setRole(role);

        List<String> errors = new ArrayList<>();
        Validator validator = new Validator();
        validator.validateAuthor(updateAuthor, errors);

        if (errors.isEmpty()) {
            authorService.addAuthor(updateAuthor);
            request.setAttribute("successMessage", "Author added successfully!");
        } else {
            request.setAttribute("errorMessages", errors);
        }

        displayAuteur(request, response);
    }

}
