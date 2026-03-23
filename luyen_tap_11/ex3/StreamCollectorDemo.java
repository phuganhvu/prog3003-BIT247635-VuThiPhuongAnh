package ex3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Employee {
    int id;
    String name;
    double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}

public class StreamCollectorDemo {
    public static void main(String[] args) {
        // Tạo danh sách 4 nhân viên với mức lương khác nhau
        List<Employee> employees = Arrays.asList(
            new Employee(1, "David", 1200),
            new Employee(2, "Alice", 900),
            new Employee(3, "Bob", 1500),
            new Employee(4, "Charlie", 800)
        );

        // Sử dụng Stream để lọc, sắp xếp và thu thập
        List<String> highSalaryNames = employees.stream()
            .filter(e -> e.getSalary() > 1000)
            .map(Employee::getName)
            .sorted()
            .collect(Collectors.toList());

        System.out.println("Danh sách tên nhân viên có lương > 1000 (đã sắp xếp Alpha-beta):");
        System.out.println(highSalaryNames);
    }
}
