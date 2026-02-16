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

    public Optional<Employee> read(String employeeName) throws IOException {
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
                    if(p[0].equals(employeeName)){
                        Employee employee = new Employee();
                        employee.setEmployeeName(p[0]);
                        employee.setSalary(Double.parseDouble(p[1]));
                        employee.setJoiningDate(LocalDateTime.parse(p[2]));
                        employee.setRole(p[3]);
                        employee.setProjectProgress(Double.parseDouble(p[4]));
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
