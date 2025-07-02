package com.dhl.virtual.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class EmployeeTest {

    @Test
    void testToString() {
        Employee emp = new Employee("Bob", "IT", 60000.0);
        String expected = "Employee{name='Bob', department='IT', salary=60000.0}";
        assertEquals(expected, emp.toString());
    }

    @Test
    void testConstructorAndGetters() {
        Employee emp = new Employee("Alice", "HR", 50000.0);
        assertEquals("Alice", emp.getName());
        assertEquals("HR", emp.getDepartment());
        assertEquals(50000.0, emp.getSalary());
    }

    @Test
    void testEmployeeWithEmptyValues() {
        Employee emp = new Employee("", "", 0.0);
        assertEquals("", emp.getName());
        assertEquals("", emp.getDepartment());
        assertEquals(0.0, emp.getSalary());
    }
}