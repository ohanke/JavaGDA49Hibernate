package org.example;

import org.example.dao.AuthorDao;
import org.example.dao.MovieDao;
import org.example.model.Author;
import org.example.model.Movie;

import java.util.List;

public class HqlExample {
    public static void main(String[] args) throws InterruptedException {
        HibernateFactory hibernateFactory = new HibernateFactory();
        MovieDao movieDao = new MovieDao(hibernateFactory);
        AuthorDao authorDao = new AuthorDao(hibernateFactory);

        List<Movie> movies = movieDao.getAllMovies();
        movies.forEach(System.out::println);

        List<Author> authors =  authorDao.getAllAuthors();
        authors.forEach(System.out::println);

        movieDao.getByTitle("Psy").forEach(System.out::println);

        authorDao.getAuthorByAge(55).forEach(System.out::println);

        hibernateFactory.getSessionFactory().close();
        Thread.sleep(2000);
    }
}
