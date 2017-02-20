package sda.finances;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by RENT on 2017-02-20.
 */
public class Application {
    public static void main(String[] args) {
        List<Expense> expenses = init();

        //1. Wyswietlamy wszystkie towary ktorych cena jednostkowa jest mniejsza od 3

        expenses.forEach(expense -> {
            expense.getProducts().stream()
                    .filter(product -> product.getUnitPrice() <= 3)
                    .forEach(product -> System.out.println(product));
        });

        System.out.println();

        //2. Wyswietlic tylko produkty spozywcze których cena jednostkowa < 3

        expenses.stream()
                .filter(expense -> expense.getType().equals("spozywcze"))
                .forEach(expense -> {
                    expense.getProducts().stream()
                            .filter(product -> product.getUnitPrice() <= 3)
                            .forEach(product -> System.out.println(product));
                });
        //3. Wyswietlamy tylko banany
        //4.
    }

    private static List<Expense> init() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("banan", 5, 6));
        products.add(new Product("piwo", 5, 6));
        products.add(new Product("mleko", 2, 3));
        products.add(new Product("chleb", 1, 2));
        products.add(new Product("cukier", 1, 1));

        Expense expense = new Expense("spozywcze", products);

        List<Product> products2 = new ArrayList<>();
        products2.add(new Product("farba", 1, 3));
        products2.add(new Product("mlotek", 1, 3));
        products2.add(new Product("gwozdzie", 1, 3));
        products2.add(new Product("nakretki", 1, 3));
        products2.add(new Product("walek", 1, 3));

        Expense expense2 = new Expense("budowlane", products2, 2017, 2, 19);

        List<Product> products3 = new ArrayList<>();
        products3.add(new Product("aspiryna", 1, 3));
        products3.add(new Product("witamina C", 1, 3));
        products3.add(new Product("syrop", 1, 3));
        products3.add(new Product("termometr", 1, 3));

        Expense expense3 = new Expense("lekarstwa", products3, 2017, 2, 18);

        List<Product> products4 = new ArrayList<>();
        products4.add(new Product("mieso", 1, 25));
        products4.add(new Product("pieczywo", 8, 1));
        products4.add(new Product("woda", 1, 2));
        products4.add(new Product("szynka", 1, 17));

        Expense expense4 = new Expense("spozywcze", products4, 2017, 2, 17);

        return Arrays.asList(expense, expense2, expense3, expense4);
    }
}
