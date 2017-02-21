package sda.finances;

import java.time.LocalDate;
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
        double banan = expenses.stream()
                .mapToDouble(expense -> expense.getProducts()
                        .stream()
                        .filter(product -> product.getName().equals("banan"))
                        .mapToDouble(product -> product.getUnitPrice() * product.getAmount())
                        .sum()
                )
                .sum();
        System.out.println(banan);
        //4. Suma cen wszystkich produktów spozywczych
        double spozywcze = expenses.stream()
                .filter(expense -> expense.getType().equals("spozywcze"))
                .mapToDouble(expense -> expense.getPrice())
                .sum();
        System.out.println(spozywcze);
        //5.Produkty kupione przed 19 lutym
        System.out.println();
        expenses.stream()
                .filter(expense -> expense.getDate().isBefore(LocalDate.of(2017, 2, 19)))
                .forEach(expense -> expense.getProducts()
                        .forEach(product -> System.out.println(product)));
        //6.Wyswietlic wydatki dla konkretnego dnia (ilosc kupionych produktow, ilosc wydanych pieniedzy)
        System.out.println();
        System.out.println(expenses.stream()
                .filter(expense -> expense.getDate().isEqual(LocalDate.of(2017, 2, 21)))
                .mapToDouble(expense -> expense.getPrice())
                .sum());
        //6.1.Wyswietlic wydatki na piwo dla konkretnego dnia
        System.out.println();
        System.out.println(expenses.stream()
                .filter(expense -> expense.getDate().isEqual(LocalDate.of(2017, 2, 21)))
                .mapToDouble(expense -> expense.getProducts()
                        .stream()
                        .filter(product -> product.getName().equals("piwo"))
                        .mapToDouble(product -> product.getUnitPrice() * product.getAmount())
                        .sum())
                .sum());
        //7.Zsumowac calkowita kwote wydana na produkty zaczynajace sie na p
        System.out.println();
        double startsWithP = expenses.stream()
                .mapToDouble(expense -> expense.getProducts()
                        .stream()
                        .filter(product -> product.getName().startsWith("p"))
                        .mapToDouble(product -> product.getUnitPrice() * product.getAmount())
                        .sum()
                )
                .sum();
        System.out.println(startsWithP);
        //8. Zsumowac koszt produktow spozywczych, ktore kupilismy w parzystych ilosciach
        System.out.println();
        System.out.println(expenses.stream()
                .filter(expense -> expense.getType().equals("spozywcze"))
                .mapToDouble(expense -> expense.getProducts()
                        .stream()
                        .filter(product -> product.getAmount() % 2 == 0)
                        .mapToDouble(product -> product.getUnitPrice() * product.getAmount())
                        .sum())
                .sum());
        //9. Z kazdego expensa wyswietlic produkt za ktorego zaplacilismy najwiecej (amount * unitPrice)
        System.out.println();
        expenses.stream()
                .map(expense -> expense.getProducts()
                        .stream()
                        .max((e1, e2) ->
                                (e1.getUnitPrice() * e1.getAmount()) > (e2.getAmount() * e2.getUnitPrice()) ? 1 : -1)
                        .get())
                .forEach(product -> System.out.println(product));

    //10. Wyswietlic najdrozszy produkt z wszystkich expensow
        System.out.println();
        System.out.println(expenses.stream()
                .map(expense -> expense.getProducts()
                        .stream()
                        .max((e1, e2) ->
                                (e1.getUnitPrice() * e1.getAmount()) > (e2.getAmount() * e2.getUnitPrice()) ? 1 : -1)
                        .get())
                .max((e1, e2) ->
                        (e1.getUnitPrice() * e1.getAmount()) > (e2.getAmount() * e2.getUnitPrice()) ? 1 : -1)
                .get());

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

        Expense expense2 = new Expense("budowlane", products2);

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
