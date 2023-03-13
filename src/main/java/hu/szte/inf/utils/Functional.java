package hu.szte.inf.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Functional {

    public static <T> List<T> iterableToList(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }

    public static <T> ObservableList<T> iterableToObservableList(Iterable<T> iterable) {
        return FXCollections.observableList(iterableToList(iterable));
    }
}
