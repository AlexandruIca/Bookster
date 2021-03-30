package Bookster;

import java.util.concurrent.atomic.AtomicLong;

/*
 * Maps a client's review to a book, measured in stars(max. 5 stars).
 */
public class BookReview implements Unique {
    /**
     * Holds a unique ID for each review that is created.
     */
    private static AtomicLong reviewID = new AtomicLong(0);

    /**
     * Get the current review ID, then be ready for the next one.
     *
     * @return the current review ID.
     * @apiNote Thread-safe.
     */
    private static Long newID() {
        return reviewID.getAndIncrement();
    }

    private Long id;
    private Book book;
    private Client client;
    private String text;
    private int numStars;

    public BookReview(Book book, Client client, String text, int numStars) {
        assert numStars <= 5;

        this.id = newID();
        this.book = book;
        this.client = client;
        this.text = text;
        this.numStars = numStars;
    }

    public Book getBook() {
        return book;
    }

    public Client getClient() {
        return client;
    }

    public String getText() {
        return text;
    }

    public int getNumStars() {
        return numStars;
    }

    @Override
    public Long getID() {
        return this.id;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append('(')
                .append(this.id)
                .append(", ")
                .append(this.book.toString())
                .append(", ")
                .append(this.client.toString())
                .append(", ")
                .append("\"")
                .append(this.text)
                .append("\"")
                .append(", ")
                .append(this.numStars)
                .append(')')
                .toString();
    }
}
