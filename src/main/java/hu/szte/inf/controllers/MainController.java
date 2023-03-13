package hu.szte.inf.controllers;

import hu.szte.inf.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private AnchorPane mainContentAnchorPane;

    @FXML
    private void initialize() throws IOException {
        App.changeView(mainContentAnchorPane, "book/read");
    }

    // TODO: implement methods specified by menu items
    //  - onClose
    //  - onBookRead
    //  - onBookCreate
    //  Also, connect them in the main view file.
}
