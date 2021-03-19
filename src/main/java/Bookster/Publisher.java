package Bookster;

import java.util.concurrent.atomic.AtomicLong;

/**
 * All books must have an associated publisher.
 */
public class Publisher implements Unique {
    /**
     * Static atomic that holds increasing IDs for authors.
     */
    private static AtomicLong publisherID = new AtomicLong(0);

    /**
     * @return a new unique ID for authors, use this when creating a new author.
     * @apiNote Method is completely thread-safe.
     */
    private static Long newID() {
        return publisherID.getAndIncrement();
    }

    private final Long id;
    private final String name;

    public Publisher(String name) {
        this.id = newID();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Long getID() {
        return this.id;
    }
}
