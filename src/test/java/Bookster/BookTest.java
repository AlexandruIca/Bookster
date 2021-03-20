package Bookster;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Vector;
import java.util.stream.IntStream;

public class BookTest {
    @Test
    public void testBookInterface() {
        var random = new Random();
        var names = new Vector<String>();
        var books = new Vector<Book>();

        names.add("These");
        names.add("Are");
        names.add("Some");
        names.add("Random");
        names.add("Names");
        names.add("For");
        names.add("You");

        final String someDate = "2012-12-12";
        final int numBooks = 100;
        final int limit = names.size();
        final int genreLimit = BookGenre.values().length;

        IntStream.range(0, numBooks).parallel().forEach(i -> {
            final var author = new Author(names.get(random.nextInt(limit)),
                    names.get(random.nextInt(limit)), someDate);
            final var publisher = new Publisher(names.get(random.nextInt(limit)));
            final var genre = BookGenre.values()[random.nextInt(genreLimit)];
            final var price = random.nextDouble() * 255.0;
            final var title = names.get(random.nextInt(limit));
            final var book = new Book(title, genre, price, someDate, author, publisher);

            if (random.nextInt() % 3 == 0) {
                book.addAuthor(new Author(names.get(random.nextInt(limit)),
                        names.get(random.nextInt(limit)), someDate));
            }
            if (random.nextInt() % 5 == 0) {
                book.addAuthor(new Author(names.get(random.nextInt(limit)),
                        names.get(random.nextInt(limit)), someDate));
            }

            books.add(book);
        });

        assert !books.isEmpty();
    }
}
