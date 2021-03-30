package Bookster;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implements all actions that can be performed in the entire app.
 * <p>
 * This is a singleton implemented using an enum to assure thread safety. Also, it just
 * seems cleaner than any other solution I've seen.
 *
 * Each member is a `HashMap` so that it can be efficient to check for the existence of an object.
 */
public enum BookService {
    INSTANCE();

    private HashMap<Long, Author> authors = new HashMap<>();
    private HashMap<Long, Publisher> publishers = new HashMap<>();
    private HashMap<Long, Client> clients = new HashMap<>();
    private HashMap<Long, Book> books = new HashMap<>();
    private HashMap<Long, Transaction> transactions = new HashMap<>();
    private HashMap<Long, BookReview> reviews = new HashMap<>();

    private BookService() {
    }

    public BookService getInstance() {
        return INSTANCE;
    }

    /**
     * @return handle to {@link #authors}, giving you maximum flexibility.
     */
    public Stream<Map.Entry<Long, Author>> authorStream() {
        return authors.entrySet().stream();
    }

    /**
     * @return handle to {@link #publishers}
     */
    public Stream<Map.Entry<Long, Publisher>> publisherStream() {
        return publishers.entrySet().stream();
    }

    /**
     * @return handle to {@link #clients}
     */
    public Stream<Map.Entry<Long, Client>> clientStream() {
        return clients.entrySet().stream();
    }

    /**
     * @return handle to {@link #books}
     */
    public Stream<Map.Entry<Long, Book>> bookStream() {
        return books.entrySet().stream();
    }

    /**
     * @return handle to {@link #transactions}
     */
    public Stream<Map.Entry<Long, Transaction>> transactionStream() {
        return transactions.entrySet().stream();
    }

    /**
     * @return handle to {@link #reviews}
     */
    public Stream<Map.Entry<Long, BookReview>> reviewStream() {
        return reviews.entrySet().stream();
    }

    private void addObject(Unique obj) throws Exception {
        if (obj instanceof Author) {
            authors.put(obj.getID(), (Author) obj);
        } else if (obj instanceof Publisher) {
            publishers.put(obj.getID(), (Publisher) obj);
        } else if (obj instanceof Client) {
            clients.put(obj.getID(), (Client) obj);
        } else if (obj instanceof Book) {
            books.put(obj.getID(), (Book) obj);
        } else if (obj instanceof Transaction) {
            transactions.put(obj.getID(), (Transaction) obj);
        } else if (obj instanceof BookReview) {
            reviews.put(obj.getID(), (BookReview) obj);
        } else {
            throw new Exception("Tried to add an object that is not of any type from package " +
                    "Bookster!");
        }
    }

    private boolean checkIfObjectExists(Unique obj) throws Exception {
        if (obj instanceof Author) {
            return authors.containsKey(obj.getID());
        } else if (obj instanceof Publisher) {
            return publishers.containsKey(obj.getID());
        } else if (obj instanceof Client) {
            return clients.containsKey(obj.getID());
        } else if (obj instanceof Book) {
            return books.containsKey(obj.getID());
        } else if (obj instanceof Transaction) {
            return transactions.containsKey(obj.getID());
        } else if (obj instanceof BookReview) {
            return reviews.containsKey(obj.getID());
        } else {
            throw new Exception("Tried to check for existence of an object that is not of any " +
                    "type from package Bookster!");
        }
    }

    /**
     * @param object new object to add to the 'database'.
     * @return true if {@code publisher} was added successfully, false otherwise.
     * <p>
     * 'false' may be returned if the object is already present, or if any exception
     * occurs when trying to add the object to the 'database'.
     */
    public boolean register(Unique object) {
        try {
            if (checkIfObjectExists(object)) {
                return false;
            }
            addObject(object);
        } catch (Exception e) {
            System.out.println("[BookService::register] Exception caught: {}" + e.getMessage());
            return false;
        }

        return true;
    }

    public List<Map.Entry<Long, Transaction>> getTransactionsOnDate(LocalDate date) {
        return this.transactionStream()
                .filter(t -> t.getValue().getDate().isEqual(date))
                .collect(Collectors.toList());
    }
}
