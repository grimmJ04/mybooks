package hu.szte.inf;

import hu.szte.inf.repositories.SqliteBookRepository;
import hu.szte.inf.utils.Functional;
import hu.szte.inf.utils.db.DbInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Stage stage;

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        stage.sizeToScene();
        return fxmlLoader.load();
    }

    public static Pane changeView(Pane pane, String fxml) throws IOException {
        pane.getChildren().clear();
        pane.getChildren().add(loadFXML(fxml));
        return pane;
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        // TODO: properly initialize application
        //  - fill db with default records
        //  - init services (if used)
        //  - create tables (if framework requires it so)

        App.stage = stage;
        Scene scene = new Scene(loadFXML("main"));
        stage.setScene(scene);
        stage.show();
    }
}