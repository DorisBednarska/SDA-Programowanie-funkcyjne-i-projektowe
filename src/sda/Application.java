package sda;

import sda.workers.AbstractEmployee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by RENT on 2017-02-18.
 */
public class Application {
    public static void main(String[] args) {
        List<AbstractEmployee> employeeList = new ArrayList<>();
        employeeList.add(new AbstractEmployee("Doris", "Bednarska", 1999, "Java"));
        employeeList.add(new AbstractEmployee("Adam", "Nowak", 2500, "Java"));
        employeeList.add(new AbstractEmployee("Michał", "Nowacki", 1750, "HR"));
        employeeList.add(new AbstractEmployee("Patryk", "Kowalski", 3500, "Java"));
        employeeList.add(new AbstractEmployee("Marcin", "Wisniewski", 4500, "Java"));

        employeeList.stream()
                .filter(e -> e.getDepartment().equals("Java"))
                .forEach(e -> System.out.println(e));
        System.out.println();
        employeeList.stream()
                .filter(e -> e.getFirstName().endsWith("a"))
                .forEach(e -> System.out.println(e));
        employeeList.stream()
                .filter(e -> e.getSalary() >= 3000)
                .forEach(e -> System.out.println(e));
        System.out.println();
        employeeList.stream()
                .filter(e -> e.getSalary() >= 3000)
                .filter(e -> e.getDepartment().equals("Java"))
                .forEach(e -> System.out.println(e));
        System.out.println();
        List<AbstractEmployee> javaEmployees = employeeList.stream()
                .filter(e -> e.getDepartment().equals("Java"))
                .collect(Collectors.toList());
        System.out.println(javaEmployees);
        System.out.println();
        employeeList.stream()
                .filter(e -> e.getLastName().equals("Bednarska"))
                .forEach(e -> System.out.println(e));
        System.out.println();
        employeeList.stream()
                .filter(e -> e.getLastName().startsWith("Bed"))
                .forEach(e -> System.out.println(e));
        System.out.println();
        Map<String, AbstractEmployee> map = employeeList.stream()
                .collect(Collectors.toMap((e -> e.getFirstName()), e -> e));
        map.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println();
        //zwracamy liste employee szukana po imie + " " + nazwisko
        employeeList.stream()
                .filter(e -> (e.getFirstName() + " " + e.getLastName()).equals("Doris Bednarska"))
                .forEach(e -> System.out.println(e));
        //sortujemy po salary
        System.out.println();
        employeeList.sort((e1, e2) -> e1.getSalary() < e2.getSalary() ? 1 : -1);
        employeeList.forEach(e -> System.out.println(e.getFirstName() + ": " + e.getSalary()));
        //wyswietlamy employee ktory zarabia najwiecej
        System.out.println();
        employeeList.sort((e1, e2) -> e1.getSalary() < e2.getSalary() ? 1 : -1);
        System.out.println(employeeList.get(0));
        System.out.println();

        AbstractEmployee richestEmployee = employeeList.stream()
                .max((e1, e2) -> e1.getSalary() > e2.getSalary() ? 1 : -1)
                .get();
        System.out.println(richestEmployee);
        //sortowanie po lastname

        //wyswietlamy employee ktory zarabia najmniej
        System.out.println();
        AbstractEmployee poorestEmployee = employeeList.stream()
                .min((e1, e2) -> e1.getSalary() > e2.getSalary() ? 1 : -1)
                .get();
        System.out.println(poorestEmployee);

        Map<String, List<AbstractEmployee>> map1 = listToMap(employeeList);
        List<AbstractEmployee> tmpList = new ArrayList<>();
        map1.entrySet().stream()
                .forEach(e -> {
                    List<AbstractEmployee> value = e.getValue();
                    value.stream()
                            .filter(e1 -> e1.getSalary() > 3000)
                            .forEach(e1 -> tmpList.add(e1));
                });
        System.out.println(tmpList);
    }

    public static Map<String, List<AbstractEmployee>> listToMap(List<AbstractEmployee> employees) {
        Map<String, List<AbstractEmployee>> map = new HashMap<>();
        for (AbstractEmployee employee : employees) {
            if (map.containsKey(employee.getDepartment())) {
                List<AbstractEmployee> listOfEmployees = map.get(employee.getDepartment());
                listOfEmployees.add(employee);
            } else {
                ArrayList<AbstractEmployee> listOfEmployeees = new ArrayList<>();
                listOfEmployeees.add(employee);
                map.put(employee.getDepartment(), listOfEmployeees);
            }
        }
        return map;
    }
}
