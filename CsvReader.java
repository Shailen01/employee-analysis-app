package employeeapp.com.util;

import java.io.*;
import java.util.*;

import employeeapp.com.model.Employee;

public class CsvReader {

    public static Map<Integer, Employee> readEmployees(String filePath) throws Exception {
        Map<Integer, Employee> map = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] a = line.split(",");

                int id = Integer.parseInt(a[0]);
                String first = a[1];
                String last = a[2];
                double salary = Double.parseDouble(a[3]);
                Integer managerId = a.length > 4 && !a[4].isBlank() ? Integer.parseInt(a[4]) : null;

                map.put(id, new Employee(id, first, last, salary, managerId));
            }
        }
        return map;
    }
}

