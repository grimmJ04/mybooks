package hu.szte.inf.utils.db;

import hu.szte.inf.models.Book;
import hu.szte.inf.models.Genre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DbInitializer {

    public static List<Book> getDefaultBooks() {
        return new ArrayList<>(Arrays.asList(
                new Book("My first book", "spider goat", 87, Genre.HORROR),
                new Book("Action book, stuff", "pig brain", 512, Genre.ACTION),
                new Book("Helicopters love", "chicken wing", 45, Genre.ROMANCE),
                new Book("Where is my brain?", "alienized", 32, Genre.SCIFI),
                new Book("Outer Wilds", "turtle back", 365, Genre.SCIFI)));
    }
}
