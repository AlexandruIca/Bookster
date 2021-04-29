package Bookster;

import com.opencsv.bean.CsvBindByName;

public class PublisherVisitor implements Visitor<Publisher> {
    public String getName() {
        return name;
    }

    @CsvBindByName(column="name", required=true)
    String name;

    @Override
    public Publisher convert() {
        return new Publisher(name);
    }
}
