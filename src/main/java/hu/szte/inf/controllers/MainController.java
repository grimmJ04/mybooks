package hu.szte.inf.controllers;

import hu.szte.inf.repositories.BookMemoryRepository;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class MainController {

    private final BookMemoryRepository repository;

    public MainController() {
        repository = new BookMemoryRepository();
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void onClose() {
        Platform.exit();
    }
}
