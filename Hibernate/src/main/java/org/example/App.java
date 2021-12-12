package org.example;

import org.example.dao.AuthorDao;
import org.example.dao.EntityDao;
import org.example.dao.MovieDao;
import org.example.model.Author;
import org.example.model.Movie;

public class App
{
    public static void main( String[] args ) throws InterruptedException {
        HibernateFactory hibernateFactory = new HibernateFactory();
        MovieDao movieDao = new MovieDao(hibernateFactory);

        Movie psy = new Movie();
        psy.setTitle("Psy");
        psy.setTime(12312313);
        psy.setType("Komedia");
        movieDao.add(psy);



        AuthorDao authorDao = new AuthorDao(hibernateFactory);
        Author jakisAutor = new Author();
        jakisAutor.setAge(55);
        jakisAutor.setName("Andrzej");
        jakisAutor.setSurname("Kowalski");
        jakisAutor.setNationality("Polska");
        authorDao.add(jakisAutor);

        EntityDao<Author> genericAuthorDao = new EntityDao<>(hibernateFactory, Author.class);
        EntityDao<Movie> genericMovieDao = new EntityDao<>(hibernateFactory, Movie.class);

        Movie psy2 = new Movie();
        psy2.setTitle("psy 2");
        psy2.setTime(123);
        psy2.setType("dramat");
        genericMovieDao.save(psy2);

        Author drugiAutor = new Author();
        drugiAutor.setNationality("Rosja");
        drugiAutor.setName("JakiesImie");
        drugiAutor.setSurname("JakiesNazwisko");
        drugiAutor.setAge(20);
        genericAuthorDao.save(drugiAutor);

        System.out.println("Sample movie get");
        System.out.println(genericMovieDao.getById(1));

        System.out.println(drugiAutor);
        drugiAutor.setName("ZmienioneImie");
        genericAuthorDao.update(drugiAutor);
        System.out.println("Po update: " + genericAuthorDao.getById(drugiAutor.getId()));

        genericAuthorDao.delete(drugiAutor);
        System.out.println("DELETE: " + genericAuthorDao.getById(drugiAutor.getId()));

        hibernateFactory.getSessionFactory().close();
        Thread.sleep(2000);
    }
}
