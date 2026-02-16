Key fields:
- employee name
- salary
- joining date
- role
- project progress

Salary increase rules:
- projectProgress < 60% ------> don't give salary increase
- floor(duration in years)
- switch role(Director: 0.05, Manager: 0.02, Employee: 0.01)

Concepts to be implemented:
- Atomic variables
- Semaphores
- Locks

flow will be:
1. pass the task(read, update, and save an employee) to as semaphore. Here there is no dependency so nothing extra to be done.
2. in each employee while processing create a new variable named new salary and create a final variable named old salary
3. old salary will be read only while new salary will be the updated salary(assumption: all the increases will be based on the old salary)
4. new salary should be an atomic variable
5. first run the project progress rule and if passed then run the other 2 rules will run together
6. after the new salary is calculated successfully, return the updated employee then save to the csv file.
7. read and write to the csv file should be done through a ReadWriteLock

Main processes:
- Read & Update an employee from/to a csv file.
- Process an employee for salary increase(read from csv -> calculate new salary -> update in csv ).
- Batch process employees.

Classes:
- Employee (Hold employee data)
- EmployeeCsvIO (Read and Update)
- SalaryIncreaseCalculator (Calculate the new salary)
- ProcessEmployeesSalaryIncreases ( Batch process all employees )