package hu.szte.inf.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Functional {

    /**
     * Converts an iterable to list.
     * This can help when converting db queries from CrudRepository.
     *
     * @param iterable The iterable object to convert.
     * @return List representation of the passed `iterable`.
     * @param <T> Generic parameter, usually there is no need to pass explicitly.
     */
    public static <T> List<T> iterableToList(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }

    /**
     * Converts an iterable to an observable list.
     * This can help when converting db queries from CrudRepository.
     * @param iterable The iterable object to convert.
     * @return Observable list representation of the passed `iterable`.
     * @param <T> Generic parameter, usually there is no need to pass explicitly.
     */
    public static <T> ObservableList<T> iterableToObservableList(Iterable<T> iterable) {
        return FXCollections.observableList(iterableToList(iterable));
    }
}
