import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

                    if(p.length < 6){
                        continue;
                    }
                    if(p[0].equals(employeeId)){
                        Employee employee = new Employee();
                        employee.setEmployeeId(p[0]);
                        employee.setEmployeeName(p[1]);
                        employee.setSalary(Double.parseDouble(p[2]));
                        if (p[3].length() == 10) {
                            employee.setJoiningDate(LocalDate.parse(p[3]).atStartOfDay());
                        }else{
                            employee.setJoiningDate(LocalDateTime.parse(p[3]));
                        }
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

    public List<Employee> readAll() throws IOException {
        lock.readLock().lock();
        try{
            List<Employee> employees = new ArrayList<>();
            if(!Files.exists(csvPath)){
                return employees;
            }else{
                List<String> lines = Files.readAllLines(csvPath);
                for(int i = 0; i < lines.size(); i++){
                    String line = lines.get(i);

                    String[] p = line.split(",", -1);

                    if(p.length < 6){
                        continue;
                    }
                    Employee employee = new Employee();
                    employee.setEmployeeId(p[0]);
                    employee.setEmployeeName(p[1]);
                    employee.setSalary(Double.parseDouble(p[2]));
                    if (p[3].length() == 10) {
                        employee.setJoiningDate(LocalDate.parse(p[3]).atStartOfDay());
                    }else{
                        employee.setJoiningDate(LocalDateTime.parse(p[3]));
                    }
                    employee.setRole(p[4]);
                    employee.setProjectProgress(Double.parseDouble(p[5]));
                    employees.add(employee);
                }
            }
            return employees;
        }
        finally {
            lock.readLock().unlock();
        }
    }

    public void update(Employee employee) throws IOException {
        lock.writeLock().lock();
        try{
            List<String> lines = new ArrayList<>();
            if (Files.exists(csvPath)){
                lines = Files.readAllLines(csvPath);
            }

            String newLine =
                    employee.getEmployeeId() + "," +
                    employee.getEmployeeName() + "," +
                    employee.getSalary() + "," +
                    employee.getJoiningDate() + "," +
                    employee.getRole() + "," +
                    employee.getProjectProgress() + ",";

            boolean updated = false;

            for(int i = 0; i < lines.size(); i++){
                String line = lines.get(i);
                if(line == null || line.isBlank()){
                    continue;
                }
                if(line.startsWith(employee.getEmployeeId()+",")){
                    lines.set(i, newLine);
                    updated = true;
                    break;
                }
            }

            if(!updated){
                lines.add(newLine);
            }

            Files.write(csvPath, lines);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
