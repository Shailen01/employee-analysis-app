package employeeapp.com.service; 


import java.util.*;

import employeeapp.com.model.Employee;

public class EmployeeService {

    Map<Integer, Employee> employees;

    public EmployeeService(Map<Integer, Employee> employeeMap) {
        this.employees = employeeMap;
        buildHierarchy();
    }

    private void buildHierarchy() {
        employees.values().forEach(emp -> {
            if (emp.managerId != null) {
                Employee manager = employees.get(emp.managerId);
                if (manager != null) {
                    manager.subordinates.add(emp);
                }
            }
        });
    }

    public void evaluateSalaries() {
        System.out.println("\n=== Salary Violations ===");

        for (Employee e : employees.values()) {
            if (e.subordinates.isEmpty()) continue;

            double avg = e.subordinates.stream()
                    .mapToDouble(s -> s.salary)
                    .average()
                    .orElse(0);

            double minAllowed = avg * 1.2;
            double maxAllowed = avg * 1.5;

            if (e.salary < minAllowed) {
                System.out.printf("%s earns %.2f LESS than allowed\n",
                        e, minAllowed - e.salary);
            } else if (e.salary > maxAllowed) {
                System.out.printf("%s earns %.2f MORE than allowed\n",
                        e, e.salary - maxAllowed);
            }
        }
    }

    public void checkReportingLines() {
        System.out.println("\n=== Long Reporting Lines ===");

        for (Employee e : employees.values()) {
            int depth = countManagers(e);

            if (depth > 4) {
                System.out.printf("%s has reporting line too long by %d levels\n",
                        e, depth - 4);
            }
        }
    }

    private int countManagers(Employee e) {
        int count = 0;
        while (e.managerId != null) {
            count++;
            e = employees.get(e.managerId);
        }
        return count;
    }
}

