package Bookster;

public interface Visitor<T extends Unique> {
    T convert();
}
