package Bookster;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Describes a transaction. Holds the book, the date of the transaction, and the client.
 */
public final class Transaction implements Unique {
    /**
     * Holds a unique ID for each transaction that is created.
     */
    private static AtomicLong transactionID = new AtomicLong(0);

    /**
     * Get the current transaction ID, then be ready for the next one.
     *
     * @return the current transaction ID.
     *
     * @apiNote Thread-safe.
     */
    private static Long newID() {
        return transactionID.getAndIncrement();
    }

    private final Long id;
    private final Book book;
    private final Client client;
    private final LocalDate date;

    public Transaction(Book book, Client client, String date) {
        this.id = newID();
        this.book = book;
        this.client = client;
        this.date = LocalDate.parse(date);
    }

    public Book getBook() {
        return this.book;
    }

    public Client getClient() {
        return this.client;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public Long getID() {
        return this.id;
    }
}
