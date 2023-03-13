package hu.szte.inf.repositories;

import hu.szte.inf.models.Book;

public class HibernateBookRepository extends HibernateRepositoryBase<Book, Long> {

    public HibernateBookRepository() {
        super(Book.class);
    }
}
