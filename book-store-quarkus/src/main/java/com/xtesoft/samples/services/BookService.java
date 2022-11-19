package com.xtesoft.samples.services;

import com.xtesoft.samples.entities.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BookService {
    @Inject
    EntityManager em;

    public List<Book> getAll() {
        List<Book> books = em.createNamedQuery("Book.findAll", Book.class).getResultList();
        return books != null ? books : new ArrayList<>();
    }

    public Book findById(Long id) {
        return em.find(Book.class, id);
    }

    @Transactional
    public void update(Book book) {
        em.merge(book);
    }

    @Transactional
    public void create(Book book) {
        em.persist(book);
    }

    @Transactional
    public void delete(Book book) {
        if (!em.contains(book)) {
            book = em.merge(book);
        }
        em.remove(book);
    }
}
