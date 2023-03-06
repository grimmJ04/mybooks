package hu.szte.inf.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Book {

    private Long id;
    @NonNull
    private String title;
    @NonNull
    private String author;
    @NonNull
    private int pageNumber;
    @NonNull
    private Genre genre;
}
