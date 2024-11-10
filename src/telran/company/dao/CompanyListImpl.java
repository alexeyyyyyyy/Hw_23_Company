package telran.company.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import telran.company.model.Employee;
import telran.company.model.SalesManager;

public class CompanyListImpl implements Company {
	String name;
	List<Employee> employees;
	int capacity;

	public CompanyListImpl(int capacity) {
		this.capacity = capacity;
		employees = new ArrayList<Employee>();
	}

	@Override
	public boolean addEmployee(Employee employee) {
		if (employee == null || capacity == employees.size() || findEmployee(employee.getId()) != null) {
			return false;
		}
		return employees.add(employee);

	}

	@Override
	public Employee removeEmployee(int id) {
		Employee victim = findEmployee(id);
		employees.remove(victim);
		return victim;
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
		double sum = 0;
		for (Employee employee : employees) {
			sum += employee.calcSalary();
		}
		return sum;
	}

	@Override
	public double averageSalary() {
		return totalSalary() / employees.size();
	}

	@Override
	public double totalSales() {
		double sum = 0;

		for (Employee employee : employees) {
			if (employee instanceof SalesManager) {
//				SalesManager sm = (SalesManager) employees[i];
//				sum+= sm.getSalesValue();
				sum += ((SalesManager) employee).getSalesValue();
			}
		}

		return sum;
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
		return findEmployeesByPredicate(e -> e.getHours() >= hours);
	}

	@Override
	public Employee[] findEmployeesSalaryBetween(double min, double max) {
//		Predicate<Employee> predicate = new Predicate<Employee>() {
//
//			@Override
//			public boolean test(Employee t) {
//				return t.calcSalary() >= min && t.calcSalary() < max;
//			}
//		};
		return findEmployeesByPredicate(t -> t.calcSalary() >= min && t.calcSalary() < max);
	}

	private Employee[] findEmployeesByPredicate(Predicate<Employee> predicate) {
		List<Employee> copyEmployees = new ArrayList<Employee>();

		for (Employee employee : employees) {
			if (predicate.test(employee)) {
				copyEmployees.add(employee);
			}
		}
		return copyEmployees.toArray(new Employee[0]);
	}

}
