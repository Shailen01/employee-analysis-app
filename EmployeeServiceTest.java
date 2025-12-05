package employeeapp.com.test;


import org.junit.jupiter.api.Test;

import employeeapp.com.model.Employee;
import employeeapp.com.service.EmployeeService;

import java.util.Map;

public class EmployeeServiceTest {

    @Test
    void testHierarchyBuilds() {
        Employee e1 = new Employee(1, "A", "L", 100, null);
        Employee e2 = new Employee(2, "B", "L", 80, 1);

        Map<Integer, Employee> map = Map.of(
                1, e1,
                2, e2
        );

        EmployeeService service = new EmployeeService(map);

        assert e1.subordinates.size() == 1;
    }
}

