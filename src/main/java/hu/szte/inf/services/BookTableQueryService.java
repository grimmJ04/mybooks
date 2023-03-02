package hu.szte.inf.services;

import hu.szte.inf.models.Book;
import javafx.collections.ObservableList;

public class BookTableQueryService extends ModelService<ObservableList<Book>> {

    private static BookTableQueryService instance;

    private BookTableQueryService() {
    }

    public static BookTableQueryService getInstance() {
        if (instance == null) instance = new BookTableQueryService();
        return instance;
    }
}
