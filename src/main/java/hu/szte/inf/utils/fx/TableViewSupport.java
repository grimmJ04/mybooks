package hu.szte.inf.utils.fx;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TableViewSupport {

    public static <T> TableView<T> createSchema(TableView<T> tableView, Class<T> clazz) {
        var items = Arrays.stream(clazz.getDeclaredFields()).map(Field::getName);

        tableView.getColumns().clear();
        tableView.getColumns().addAll(items.map(el -> {
            TableColumn<T, ?> col = new TableColumn<>(el);
            col.setCellValueFactory(new PropertyValueFactory<>(el));
            return col;
        }).collect(Collectors.toList()));

        return tableView;
    }

    public static <T> TableView<T> fillRows(TableView<T> tableView, Iterable<T> data, Class<T> clazz) {
        var newTableView = createSchema(tableView, clazz);
        newTableView.setItems(FXCollections.observableList(
                StreamSupport.stream(data.spliterator(), false).collect(Collectors.toList())));
        return newTableView;
    }
}
