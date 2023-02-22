package hu.szte.inf.controllers;

import hu.szte.inf.App;
import hu.szte.inf.repositories.BookMemoryRepository;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private final BookMemoryRepository repository;

    @FXML
    private AnchorPane mainContentAnchorPane;

    public MainController() {
        repository = new BookMemoryRepository();
    }

    @FXML
    private void initialize() throws IOException {
        changeView("book/read");
    }

    private void changeView(String fxml) throws IOException {
        mainContentAnchorPane.getChildren().clear();
        mainContentAnchorPane.getChildren().add(App.loadFXML(fxml));
    }

    @FXML
    private void onClose() {
        Platform.exit();
    }

    @FXML
    private void createBook() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(App.loadFXML("book/create")));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void readBooks() throws IOException {
        changeView("book/read");
    }
}
