package com.dhl.virtusa;

import com.dhl.virtusa.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class VirtusaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtusaApplication.class, args);

		List<String> employee = new ArrayList<String>();
		employee.stream().sorted();
		

		FeaturePrepUtil instance = FeaturePrepUtil.getInstance();

		instance.findLongestString();
		instance.findAvgOfGivenNumber();
	}



	//For exmple: "ABCABCBB" , Result : 3 exap: BBBBB result 1
 // PWWKEW result should be 3
	private static void printMessage(String msg){
		System.out.println(msg);
	}

	private static void streamPrep(){
		// List of employees
		List<Employee> employees = Arrays.asList(
				new Employee("Alice", "IT", 70000),
				new Employee("Bob", "HR", 60000),
				new Employee("Charlie", "IT", 80000),
				new Employee("David", "IT", 75000),
				new Employee("Eve", "Finance", 65000)
		);

		Optional<Employee> maxSalaryEmployee = employees.stream()
				.filter(e -> "IT".equals(e.getDepartment())) // Filter IT employees
				.max(Comparator.comparingDouble(Employee::getSalary)); // Find max by salary

		Optional<Employee> max = employees.stream().filter(e -> e.getDepartment().equals("IT"))
				.max(Comparator.comparingDouble(Employee::getSalary));
		// Print the result
		if (maxSalaryEmployee.isPresent()) {
			System.out.println("Employee with the highest salary in IT department: " + maxSalaryEmployee.get());
		} else {
			System.out.println("No employees found in the IT department.");
		}
	}

}