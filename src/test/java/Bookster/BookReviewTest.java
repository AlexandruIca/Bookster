package Bookster;

import org.junit.jupiter.api.Test;

public class BookReviewTest {
    @Test
    public void testToString() {
        var author = new Author("A", "B", "2000-01-01");
        var publisher = new Publisher("X");
        var client = new Client("Client", "Client", "2000-01-01");
        var book = new Book("Title", BookGenre.BUSINESS, 19.99, "2000-01-01", author, publisher);
        var review = new BookReview(book, client, "Review.", 5);
        final var expected = new StringBuilder()
                .append('(')
                .append(review.getID())
                .append(", ")
                .append(book.toString())
                .append(", ")
                .append(client.toString())
                .append(", ")
                .append("\"")
                .append(review.getText())
                .append("\"")
                .append(", ")
                .append(review.getNumStars())
                .append(')')
                .toString();

        assert review.toString().equals(expected);
    }
}
