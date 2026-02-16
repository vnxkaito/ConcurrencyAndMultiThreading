import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class main {
    public static void main(String[] args) throws IOException {
        ProcessEmployeesSalaryIncreases processEmployeesSalaryIncreases = new ProcessEmployeesSalaryIncreases();
        processEmployeesSalaryIncreases.processAllEmployees();
//        EmployeeCsvIO employeeCsvIO = new EmployeeCsvIO(Path.of("data\\Employees.csv"));
//        List<Employee> employees = employeeCsvIO.readAll();
//        System.out.println(employees.size());

//        Optional<Employee> employee = employeeCsvIO.read("1");
//        if(employee.isPresent()){
//            Employee employeeObject = employee.get();
//            System.out.println(employeeObject.getEmployeeName());
//            employeeObject.setEmployeeName(employeeObject.getEmployeeName()+"Updated");
//            employeeCsvIO.update(employeeObject);
//        }else{
//            System.out.println("Employee not found");
//        }
    }
}
