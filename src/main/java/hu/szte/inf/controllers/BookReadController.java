package hu.szte.inf.controllers;

import hu.szte.inf.models.Book;
import hu.szte.inf.services.BookTableQueryService;
import hu.szte.inf.utils.fx.TableViewSupport;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class BookReadController {

    private final BookTableQueryService tableService = BookTableQueryService.getInstance();

    @FXML
    private TableView<Book> tableView;

    @FXML
    private void initialize() {
        TableViewSupport.createSchema(tableView, Book.class);
        tableView.itemsProperty().bind(tableService.modelProperty());
    }
}
