package org.example.dao;

import lombok.AllArgsConstructor;
import org.example.HibernateFactory;
import org.example.model.Author;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

@AllArgsConstructor
public class AuthorDao {
    private HibernateFactory hibernateFactory;

    public void add(Author author){
        SessionFactory sessionFactory = hibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(author);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    public Author getById(Integer id){
        SessionFactory sessionFactory = hibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Author author = session.find(Author.class, id);
        session.close();
        sessionFactory.close();
        return author;
    }

    public List<Author> getAllAuthors() {
        SessionFactory sessionFactory = hibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Author> fromAuthor = session.createQuery("From Author", Author.class).getResultList();
        session.close();
        return fromAuthor;
    }

    public List<Author> getAuthorByAge(int ageFromMethod){
        Session session = hibernateFactory.getSessionFactory().openSession();
        Query<Author> query = session.createQuery("From Author a WHERE a.age = :ageToFind", Author.class);
        query.setParameter("ageToFind", ageFromMethod);
        List<Author> authors = query.list();
        session.close();
        return authors;
    }
}
