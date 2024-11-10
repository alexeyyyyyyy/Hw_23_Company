package telran.company.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.company.dao.Company;
import telran.company.dao.CompanyImpl;
import telran.company.dao.CompanyListImpl;
import telran.company.dao.CompanySetImpl;
import telran.company.model.Employee;
import telran.company.model.Manager;
import telran.company.model.SalesManager;
import telran.company.model.WageEmployee;

class CompanyTest {
	Company company;
	Employee[] firm;

	@BeforeEach
	void setUp() throws Exception {
		company = new CompanySetImpl(5);
		firm = new Employee[4];
		firm[0] = new Manager(1000, "John", "Smith", 182, 20_000, 2);
		firm[1] = new WageEmployee(2000, "Mary", "Smith", 182, 40);
		firm[2] = new SalesManager(3000, "Peter", "Jackson", 182, 40_000, 0.1);
		firm[3] = new SalesManager(4000, "Tigran", "Petrosian", 91, 80_000, 0.1);
		for (int i = 0; i < firm.length; i++) {
			company.addEmployee(firm[i]);
		}
	}

	@Test
	void testAddEmployee() {
		assertFalse(company.addEmployee(firm[3]));
		Employee employee = new SalesManager(5000, "Sergey", "Petrov", 182, 160_000, 0.2);
		assertTrue(company.addEmployee(employee));
		assertEquals(5, company.size());
		employee = new SalesManager(6000, "Sergey", "Petrov", 182, 160_000, 0.2);
		assertFalse(company.addEmployee(employee));
	}

	@Test
	void testRemoveEmployee() {
		assertEquals(firm[2], company.removeEmployee(3000));
		assertEquals(3, company.size());
//		Employee employee = company.removeEmployee(3000);
//		assertEquals(firm[2], employee);
//		assertEquals(3, company.size());

		assertNull(company.removeEmployee(3000));
	}

	@Test
	void testFindEmployee() {
		assertEquals(firm[2], company.findEmployee(3000));

		assertNull(company.findEmployee(5000));
	}

	@Test
	void testTotalSalary() {
		assertEquals(61104.0, company.totalSalary());
	}

	@Test
	void testAverageSalary() {
		assertEquals(61104.0 / 4, company.averageSalary());
	}

	@Test
	void testTotalSales() {
		assertEquals(120_000, company.totalSales());
	}

	@Test
	void testSize() {
		assertEquals(4, company.size());
	}

	@Test
	void testPrintEmployees() {
		company.printEmployees();
	}

	@Test
	void testFindEmployeesHoursGreateThan() {
		Employee[] actual = company.findEmployeesHoursGreateThan(100);
		Employee[] expected = { firm[0], firm[1], firm[2] };
		assertArrayEquals(expected, actual);
	}

	@Test
	void testFindEmployeesSalaryBetween() {
		Employee[] actual = company.findEmployeesSalaryBetween(5000, 8000);
		Employee[] expected = { firm[1], firm[2] };
		assertArrayEquals(expected, actual);
	}

}
