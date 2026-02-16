import java.time.LocalDateTime;

public class Employee {
    private String employeeId;
    private String employeeName;
    private double salary;
    private LocalDateTime joiningDate;
    private String role;
    private double projectProgress;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDateTime getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDateTime joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getProjectProgress() {
        return projectProgress;
    }

    public void setProjectProgress(double projectProgress) {
        this.projectProgress = projectProgress;
    }
}
