package hu.szte.inf.controllers;

import hu.szte.inf.repositories.BookRepository;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class MainController {

    private final BookRepository repository;

    public MainController() {
        repository = new BookRepository();
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void onClose() {
        Platform.exit();
    }
}
