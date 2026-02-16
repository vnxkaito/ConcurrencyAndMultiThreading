import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class EmployeeCsvIO {
    private final Path csvPath;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final static Path projectPath = Path.of(System.getProperty("user.dir"));

    public EmployeeCsvIO(Path csvPath){
        this.csvPath = projectPath.resolve(csvPath);
    }

    public Optional<Employee> read(String employeeId) throws IOException {
        lock.readLock().lock();
        try{
            if(!Files.exists(csvPath)){
                return Optional.empty();
            }else{
                List<String> lines = Files.readAllLines(csvPath);
                for(int i = 0; i < lines.size(); i++){
                    String line = lines.get(i);

                    String[] p = line.split(",", -1);

                    if(p.length < 5){
                        continue;
                    }
                    if(p[0].equals(employeeId)){
                        Employee employee = new Employee();
                        employee.setEmployeeId(p[0]);
                        employee.setEmployeeName(p[1]);
                        employee.setSalary(Double.parseDouble(p[2]));
                        employee.setJoiningDate(LocalDateTime.parse(p[3]));
                        employee.setRole(p[4]);
                        employee.setProjectProgress(Double.parseDouble(p[5]));
                        return Optional.of(employee);
                    }
                }
            }
            return Optional.empty();
        }
        finally {
            lock.readLock().unlock();
        }
    }
}
