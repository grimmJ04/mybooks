package hu.szte.inf.controllers;

import hu.szte.inf.models.Book;
import hu.szte.inf.models.Genre;
import hu.szte.inf.repositories.BookMemoryRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Arrays;

public class BookCreateController {

    private final BookMemoryRepository repository = new BookMemoryRepository();

    @FXML
    private TextField titleTextField;
    @FXML
    private TextField authorTextField;
    @FXML
    private Spinner<Integer> pageNumberSpinner;
    @FXML
    private ComboBox<Genre> genreComboBox;

    @FXML
    private void initialize() {
        genreComboBox.setItems(
                FXCollections.observableList(Arrays.stream(Genre.values()).toList()));
        genreComboBox.getSelectionModel().selectFirst();
    }

    private Book getModelFromInputs() {
        return new Book(
                titleTextField.getText(),
                authorTextField.getText(),
                pageNumberSpinner.getValue(),
                genreComboBox.getValue());
    }

    private void closeStage(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onSave(ActionEvent actionEvent) {
        var model = getModelFromInputs();
        repository.save(model);
        closeStage(actionEvent);
    }

    @FXML
    private void onCancel(ActionEvent actionEvent) {
        closeStage(actionEvent);
    }
}
