package Bookster;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Description of a book.
 */
public final class Book implements Unique {
    /**
     * Static atomic that holds increasing IDs for authors.
     */
    private static AtomicLong bookID = new AtomicLong(0);

    /**
     * @return a new unique ID for books, use this when creating a new book.
     * @apiNote Method is completely thread-safe.
     */
    private static Long newID() {
        return bookID.getAndIncrement();
    }

    private final Long id;
    private final BookGenre genre;
    private final String title;
    private final double price;
    private final LocalDate datePublished;
    private final ArrayList<Author> authors = new ArrayList<>();
    private final Publisher publisher;

    public Book(String title, BookGenre genre, double price, String datePublished, Author author,
                Publisher publisher) {
        this.id = newID();
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.datePublished = LocalDate.parse(datePublished);
        this.authors.add(author);
        this.publisher = publisher;
    }

    /**
     * A book may have multiple authors, use this method to add more.
     *
     * @param author new author to add
     */
    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public ArrayList<Author> getAuthors() {
        return this.authors;
    }

    public String getTitle() {
        return this.title;
    }

    public BookGenre getGenre() {
        return this.genre;
    }

    public double getPrice() {
        return this.price;
    }

    public LocalDate getDatePublished() {
        return this.datePublished;
    }

    public Publisher getPublisher() {
        return this.publisher;
    }

    @Override
    public Long getID() {
        return this.id;
    }
}
