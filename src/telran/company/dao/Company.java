package telran.company.dao;

import telran.company.model.Employee;

public interface Company {

	boolean addEmployee(Employee employee);

	Employee removeEmployee(int id);

	Employee findEmployee(int id);

	double totalSalary();

	double averageSalary();

	double totalSales();

	int size();

	void printEmployees();

	Employee[] findEmployeesHoursGreateThan(int hours);

	Employee[] findEmployeesSalaryBetween(double min, double max);

}
