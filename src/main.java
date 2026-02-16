import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public class main {
    public static void main(String[] args) throws IOException {
        EmployeeCsvIO employeeCsvIO = new EmployeeCsvIO(Path.of("data\\Employees.csv"));
        Optional<Employee> employee = employeeCsvIO.read("1");
        if(employee.isPresent()){
            System.out.println(employee.get().getEmployeeName());
        }else{
            System.out.println("Employee not found");
        }
    }
}
