package hu.szte.inf.controllers;

import hu.szte.inf.models.Book;
import hu.szte.inf.repositories.BookMemoryRepository;
import hu.szte.inf.utils.fx.TableViewSupport;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class BookReadController {

    private final BookMemoryRepository repository = new BookMemoryRepository();

    @FXML
    private TableView<Book> tableView;

    @FXML
    private void initialize() {
        TableViewSupport.fillRows(tableView, repository.findAll(), Book.class);
    }
}
