package hu.szte.inf.repositories;

import hu.szte.inf.models.Book;
import hu.szte.inf.utils.PersistenceSupport;

public class HibernateBookRepository extends HibernateRepositoryBase<Book, Long> {

    public HibernateBookRepository() {
        super(PersistenceSupport.getInstance("global"), Book.class);
    }
}
