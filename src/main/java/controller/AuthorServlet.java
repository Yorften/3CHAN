package controller;

import model.Author;
import model.enums.Role;
import repository.implementation.AuthorRepositoryImpl;
import repository.interfaces.AuthorRepository;
import service.AuthorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class AuthorServlet extends HttpServlet {

	private AuthorRepository authorRepository = new AuthorRepositoryImpl();
    private AuthorService authorService = new AuthorService(authorRepository);



    public void displayAuteur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Author> authors = authorService.getAllAuthors();
        request.setAttribute("authors", authors);
        request.getRequestDispatcher("/views/author.jsp").forward(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       displayAuteur(request,response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action!=null && action.equals("add")) {
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

            authorService.addAuthor(newAuthor);
            request.setAttribute("successMessage", "Auteur ajouté avec succès !");
           displayAuteur(request,response);

        }else if(action.equals("delete")){

            String authorIdString = request.getParameter("id");
            long authorId = Integer.parseInt(authorIdString);

            authorService.deleteAuthor(authorId);

            request.setAttribute("successMessage", "Author deleted successfully!");

            displayAuteur(request,response);

        }else if (action != null && action.equals("update")) {
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

            if (updateAuthor != null) {

                updateAuthor.setFirstName(firstName);
                updateAuthor.setLastName(lastName);
                updateAuthor.setEmail(email);
                updateAuthor.setBirthDay(birthDate);
                updateAuthor.setRole(role);


                authorService.updateAuthor(updateAuthor);

                request.setAttribute("successMessage", "Author updated successfully!");
            } else {
                request.setAttribute("errorMessage", "Author not found.");
            }


            displayAuteur(request, response);
        }

    }









}
