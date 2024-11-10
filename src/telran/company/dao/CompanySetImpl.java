package telran.company.dao;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import telran.company.model.Employee;
import telran.company.model.SalesManager;

public class CompanySetImpl implements Company {
	String name;
	Set<Employee> employees;
	
	int capacity;

	public CompanySetImpl(int capacity) {
		this.capacity = capacity;
		employees = new HashSet<Employee>();
	}

	@Override
	public boolean addEmployee(Employee employee) {
		if (employees.size() < capacity) {
			return employees.add(employee);
		}
		return false;

	}

	@Override
	public Employee removeEmployee(int id) {
		for (Employee employee : employees) {
			if (employee.getId() == id) {
				employees.remove(employee);
				return employee;
			}
		}
		return null;
	}

	@Override
	public Employee findEmployee(int id) {
		for (Employee employee : employees) {
			if (employee.getId() == id) {
				return employee;
			}
		}
		return null;
	}

	@Override
	public double totalSalary() {
		double total = 0.0;
		for (Employee employee : employees) {
			total += employee.getHours();
		}
		return total;
	}

	@Override
	public double averageSalary() {
		if (employees.isEmpty()) {
			return 0.0;
		}

		double total = 0.0;
		for (Employee employee : employees) {
			total += employee.calcSalary();
		}
		return total;
	}

	@Override
	public double totalSales() {
		double total = 0.0;
		for (Employee employee : employees) {
			if (employee instanceof SalesManager) {
				SalesManager salesManager = (SalesManager) employee;
				total += salesManager.getSalesValue() * salesManager.getPercent();
			}
		}
		return total;
	}

	@Override
	public int size() {
		return employees.size();
	}

	@Override
	public void printEmployees() {
		for (Employee employee : employees) {
			System.out.println(employee);
		}

	}

	@Override
	public Employee[] findEmployeesHoursGreateThan(int hours) {
		return findEmployeesByPredicate(employee -> employee.getHours() > hours);
	}

	@Override
	public Employee[] findEmployeesSalaryBetween(double min, double max) {
		return findEmployeesByPredicate(employee -> employee.calcSalary() >= min && employee.calcSalary() <= max);
	}

	private Employee[] findEmployeesByPredicate(Predicate<Employee> predicate) {
		int count = 0;
		for (Employee employee : employees) {
			if (predicate.test(employee)) {
				count++;
			}
		}
		Employee[] result = new Employee[count];
		int index = 0;
		for (Employee employee : employees) {
			if (predicate.test(employee)) {
				result[index++] = employee;
			}
		}
		return result;
	}

	interface EmployeePredicate {
		boolean test(Employee employee);
	}
}
