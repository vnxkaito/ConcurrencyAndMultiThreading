import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProcessEmployeesSalaryIncreases {
    private static final Semaphore sem = new Semaphore(8);
    private static final Lock salaryLock = new ReentrantLock();

    // read all employees
    // pass each employee to the method processSalaryIncrease. Method should save the employee after finishing.

    public void processSalaryIncrease(Employee employee) throws IOException {
        List<Employee> employees = new EmployeeCsvIO(Path.of("data\\Employees.csv")).readAll();
        employees.forEach(emp -> {
            try{
                sem.acquire();
                updateSalaryWithIncrease(emp);
            } catch (InterruptedException ex){
                Thread.currentThread().interrupt();
            } finally {
                sem.release();
            }
        });
    }

    private static void updateSalaryWithIncrease(Employee employee){
        final double oldSalary = employee.getSalary();

        AtomicReference<Double> newSalary = new AtomicReference<>(oldSalary);

        // run the salary increase logic here
        if(employee.getProjectProgress() >= 0.6){
            newSalary.set(oldSalary + ( oldSalary * Math.floor(employee.getWorkingYears()) * 0.02 ));

            if(employee.getRole().equalsIgnoreCase("director")){
                newSalary.set(oldSalary + ( oldSalary * 0.05 ));
            }else if(employee.getRole().equalsIgnoreCase("manager")){
                newSalary.set(oldSalary + ( oldSalary * 0.02 ));
            }else if(employee.getRole().equalsIgnoreCase("employee")){
                newSalary.set(oldSalary + ( oldSalary * 0.01 ));
            }
        }
    }
}
