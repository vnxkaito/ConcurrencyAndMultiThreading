import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ProcessEmployeesSalaryIncreases {
    // read all employees
    // pass each employee to the method processSalaryIncrease. Method should save the employee after finishing.

    public void processSalaryIncrease(Employee employee) throws IOException {
        List<Employee> employees = new EmployeeCsvIO(Path.of("data\\Employees.csv")).readAll();
        employees.forEach(ProcessEmployeesSalaryIncreases::updateSalaryWithIncrease);
    }

    private static void updateSalaryWithIncrease(Employee employee){
        // run the salary increase logic here
        if(employee.getProjectProgress() >= 0.6){

        }
    }
}
