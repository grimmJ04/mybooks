package hu.szte.inf.controllers;

import hu.szte.inf.models.Book;
import hu.szte.inf.repositories.BookRepository;
import hu.szte.inf.services.BookTableQueryService;
import hu.szte.inf.utils.Functional;
import hu.szte.inf.utils.fx.TableViewSupport;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BookReadController {

    private final BookRepository repository = new BookRepository();
    private final BookTableQueryService tableService = BookTableQueryService.getInstance();

    @FXML
    private TableView<Book> tableView;
    @FXML
    private TextField selectedIdTextField;

    @FXML
    private void initialize() {
        TableViewSupport.createSchema(tableView, Book.class);
        // TODO: implement filling of table
    }

    @FXML
    private void onDelete() {
        // TODO: implement delete method
    }

    @FXML
    private void onClear() {
        tableView.getSelectionModel().clearSelection();
    }
}
