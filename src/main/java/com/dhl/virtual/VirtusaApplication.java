package com.dhl.virtual;

import com.dhl.virtual.model.Employee;
import com.dhl.virtual.redish_service.RedishCacheServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;

@SpringBootApplication
public class VirtusaApplication {
	private static final Logger logger = LoggerFactory.getLogger(RedishCacheServiceImp.class);

	public static void main(String[] args) {
		SpringApplication.run(VirtusaApplication.class, args);

		List<String> employee = new ArrayList<String>();
		employee.stream().sorted();
		

		FeaturePrepUtil instance = FeaturePrepUtil.getInstance();

		instance.findLongestString();
		instance.findAvgOfGivenNumber();


		ApplicationContext context = SpringApplication.run(VirtusaApplication.class, args);
		RedisTemplate<String, Object> redisTemplate = context.getBean(RedisTemplate.class);

		RedishCacheServiceImp redishCacheServiceImp = new RedishCacheServiceImp(redisTemplate);
		long cacheRecordCount = redishCacheServiceImp.countKeys();
		logger.info("Redish Cache Count --->  {}", cacheRecordCount);

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