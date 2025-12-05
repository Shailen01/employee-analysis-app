package employeeapp.com.employee;


import employeeapp.com.service.EmployeeService;
import employeeapp.com.util.CsvReader;

public class Main {
    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            System.err.println("Usage: java -jar app.jar employees.csv");
            return;
        }

        var employees = CsvReader.readEmployees(args[0]);
        var service = new EmployeeService(employees);

        service.evaluateSalaries();
        service.checkReportingLines();
    }
}

