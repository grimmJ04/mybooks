package hu.szte.inf.repositories;

import hu.szte.inf.models.Book;

public class BookRepository extends RepositoryBase<Book, Long> {

    public BookRepository() {
        super(Book.class);
    }
}
