package com.artnast.springserver;

import com.artnast.springserver.model.Employee;
import com.artnast.springserver.model.EmployeeDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringServerApplicationTests {

	@Autowired
	private EmployeeDao employeeDao;

	//@Test
	void addEmployeeTest() {
		Employee employee = new Employee();
		employee.setName("Pavel Pavlov");
		employee.setLocation("UK");
		employee.setBranch("IT");
		employeeDao.save(employee);
	}

	//@Test
	void getAllEmployees() {
		List<Employee> employeeList = employeeDao.getAllEmployees();
		System.out.println(employeeList);
	}

	//@Test
	void getAllEmployeesAndDeleteThem() {
		List<Employee> employeeList = employeeDao.getAllEmployees();
		for (Employee employee: employeeList)
			employeeDao.delete(employee);
	}



}
