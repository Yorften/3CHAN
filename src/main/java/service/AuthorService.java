package service;

import model.Author;
import repository.interfaces.AuthorRepository;

import java.util.List;

public class AuthorService {
    public  final AuthorRepository authorRepository;
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /*
        * public void addAuthor(Author author);
        public void updateAuthor(Author author);
        public void deleteAuthor(Long authorId);

        * */



    public void addAuthor(Author author){
      authorRepository.addAuthor(author);
    }

    public void updateAuthor(Author author){
        authorRepository.updateAuthor(author);
    }

    public void deleteAuthor(Long authorId){
        authorRepository.deleteAuthor(authorId);
    }

    public List<Author> getAllAuthors(){
         return  authorRepository.getAllAuthors();
    }






}
