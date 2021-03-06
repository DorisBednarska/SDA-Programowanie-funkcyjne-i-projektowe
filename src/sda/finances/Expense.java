package sda.finances;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by RENT on 2017-02-20.
 */
public class Expense {
    private String type;
    private List<Product> products;
    private LocalDate date;
    private double price;

    public Expense(String type, List<Product> products, int year, int month, int dayOfMonth) {
        this.type = type;
        this.products = products;
        this.date = LocalDate.of(year, month, dayOfMonth);
        this.price = countPrice();
    }

    public Expense(String type, List<Product> products) {
        this.type = type;
        this.products = products;
        this.date = LocalDate.now();
        this.price = countPrice();
    }

    private double countPrice() {
        double tmpPrice = 0;
        for (Product product : products) {
            tmpPrice += product.getAmount() * product.getUnitPrice();
        }
        return tmpPrice;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "type='" + type + '\'' +
                ", products=" + products +
                ", date=" + date +
                ", price=" + price +
                '}';
    }

    public String getType() {
        return type;
    }

    public List<Product> getProducts() {
        return products;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }
}
