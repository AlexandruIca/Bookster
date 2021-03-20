package App;

import Bookster.Author;
import Bookster.BookService;

public class App {
    private static void populate(BookService srv) {
        // authors
        srv.registerAuthor(new Author("Mihai", "Eminescu", "1850-01-15"));
        srv.registerAuthor(new Author("Ion", "Creanga", "1837-03-13"));
        srv.registerAuthor(new Author("Ion Luca", "Caragiale", "1852-03-13"));
        srv.registerAuthor(new Author("Ioan", "Slavici", "1848-01-18"));
        srv.registerAuthor(new Author("Marin", "Preda", "1840-01-15"));
        srv.registerAuthor(new Author("Titu", "Maiorescu", "1840-02-15"));
    }

    public static void main(String[] args) {
        BookService srv = BookService.INSTANCE;
        populate(srv);
        srv.authorStream().forEach(a -> System.out.println(a.getValue()));
    }
}
