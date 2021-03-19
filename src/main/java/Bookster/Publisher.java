package Bookster;

import java.util.concurrent.atomic.AtomicLong;

/**
 * All books must have an associated publisher.
 */
public final class Publisher implements Unique {
    /**
     * Static atomic that holds increasing IDs for publishers.
     */
    private static AtomicLong publisherID = new AtomicLong(0);

    /**
     * @return a new unique ID for publishers, use this when creating a new publisher.
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
