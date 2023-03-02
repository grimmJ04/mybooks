package hu.szte.inf.services;

import hu.szte.inf.models.Book;

public class BookSelectionService extends ModelService<Book> {

    private static BookSelectionService instance;

    private BookSelectionService() {
    }

    public static BookSelectionService getInstance() {
        if (instance == null) instance = new BookSelectionService();
        return instance;
    }
}
