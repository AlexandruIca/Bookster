package Bookster;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

/**
 * Implements all actions that can be performed in the entire app.
 *
 * @note This is a singleton implemented using an enum to assure thread safety. Also, it just
 * seems
 * cleaner than any other solution I've seen
 */
public enum BookService {
    INSTANCE();

    private HashMap<Long, Author> authors;

    private BookService() {
        authors = new HashMap<Long, Author>();
    }

    public BookService getInstance() {
        return INSTANCE;
    }

    /**
     * @param author new author to add to the 'database'.
     * @return true if {@code author} was added successfully, false otherwise.
     * @note false may be returned if the author is already present, or if any exception
     * occurs when trying to add the author to {@link #authors}.
     */
    public boolean registerAuthor(Author author) {
        try {
            if (authors.containsKey(author.getID())) {
                return false;
            }
            authors.put(author.getID(), author);
        } catch (Exception e) {
            System.out.println("[BookService::registerAuthor] Exception caught: {}" + e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * @return handle to {@link #authors}, giving you maximum flexibility.
     */
    public Stream<Map.Entry<Long, Author>> authorStream() {
        return authors.entrySet().stream();
    }
}
