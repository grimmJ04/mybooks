package hu.szte.inf;

import hu.szte.inf.repositories.HibernateBookRepository;
import hu.szte.inf.services.BookTableQueryService;
import hu.szte.inf.utils.Functional;
import hu.szte.inf.utils.db.DbInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Stage stage;

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        stage.sizeToScene();
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        var repository = new HibernateBookRepository();
        repository.saveAll(DbInitializer.getDefaultBooks());
        BookTableQueryService.getInstance().setModel(Functional.iterableToObservableList(repository.findAll()));

        App.stage = stage;
        Scene scene = new Scene(loadFXML("main"));
        stage.setScene(scene);
        stage.show();
    }
}