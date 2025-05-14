/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

// Employee class to store employee details
class Employee {
    int id;
    String name;
    int age;
    String address;
    String role;
    double salary;

    public Employee(int id, String name, int age, String address, String role, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.role = role;
        this.salary = salary;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age +
               ", Address: " + address + ", Role: " + role + ", Salary: " + salary;
    }
}

// Main class for Staff Management System
public class StaffManagementSystem {

    // In-memory database
    HashMap<Integer, Employee> employees = new HashMap<>();

    // Main method
    public static void main(String[] args) {
        StaffManagementSystem sms = new StaffManagementSystem();
        sms.displayMainMenu();
    }

    // Display the main menu GUI
    public void displayMainMenu() {
        JFrame frame = new JFrame("Staff Management System");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton insertBtn = new JButton("Insert Employee");
        JButton searchBtn = new JButton("Search by ID");
        JButton editBtn = new JButton("Edit Employee");
        JButton deleteBtn = new JButton("Delete Employee");
        JButton highWageBtn = new JButton("Employees with Salary > 20k");
        JButton displayAllBtn = new JButton("Display All Employees");

        insertBtn.addActionListener(e -> insertEmployee());
        searchBtn.addActionListener(e -> searchEmployeeById());
        editBtn.addActionListener(e -> editEmployeeDetails());
        deleteBtn.addActionListener(e -> deleteEmployee());
        highWageBtn.addActionListener(e -> searchEmployeesWithHighWage());
        displayAllBtn.addActionListener(e -> displayRecordList());

        frame.setLayout(new GridLayout(6, 1));
        frame.add(insertBtn);
        frame.add(searchBtn);
        frame.add(editBtn);
        frame.add(deleteBtn);
        frame.add(highWageBtn);
        frame.add(displayAllBtn);

        frame.setVisible(true);
    }

    // Insert a new employee
    public void insertEmployee() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID:"));
            if (employees.containsKey(id)) {
                JOptionPane.showMessageDialog(null, "Employee already exists!");
                return;
            }

            String name = JOptionPane.showInputDialog("Enter Name:");
            int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Age:"));
            String address = JOptionPane.showInputDialog("Enter Address:");
            String role = JOptionPane.showInputDialog("Enter Role:");
            double salary = Double.parseDouble(JOptionPane.showInputDialog("Enter Salary:"));

            if (!EmployeeValidator.validate(id, name, age, address, role, salary)) return;

            Employee emp = new Employee(id, name, age, address, role, salary);
            employees.put(id, emp);
            JOptionPane.showMessageDialog(null, "Employee added successfully!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input!");
        }
    }

    // Search employee by ID
    public void searchEmployeeById() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to search:"));
            if (employees.containsKey(id)) {
                JOptionPane.showMessageDialog(null, employees.get(id).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Employee not found!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid ID!");
        }
    }

    // Edit employee details
    public void editEmployeeDetails() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to edit:"));
            if (employees.containsKey(id)) {
                String name = JOptionPane.showInputDialog("Enter New Name:");
                int age = Integer.parseInt(JOptionPane.showInputDialog("Enter New Age:"));
                String address = JOptionPane.showInputDialog("Enter New Address:");
                String role = JOptionPane.showInputDialog("Enter New Role:");
                double salary = Double.parseDouble(JOptionPane.showInputDialog("Enter New Salary:"));

                if (!EmployeeValidator.validate(id, name, age, address, role, salary)) return;

                employees.put(id, new Employee(id, name, age, address, role, salary));
                JOptionPane.showMessageDialog(null, "Employee updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Employee not found!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input!");
        }
    }

    // Delete employee by ID
    public void deleteEmployee() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to delete:"));
            if (employees.remove(id) != null) {
                JOptionPane.showMessageDialog(null, "Employee deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Employee not found!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid ID!");
        }
    }

    // Search employees with salary > 20000
    public void searchEmployeesWithHighWage() {
        StringBuilder result = new StringBuilder("Employees with salary > 20000:\n");
        for (Employee emp : employees.values()) {
            if (emp.salary > 20000) {
                result.append(emp.toString()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, result.length() == 37 ? "None found." : result.toString());
    }

    // Display all employee records
    public void displayRecordList() {
        StringBuilder result = new StringBuilder("All Employee Records:\n");
        for (Employee emp : employees.values()) {
            result.append(emp.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, result.length() == 23 ? "No records available." : result.toString());
    }
}

// Validator class for input validation
class EmployeeValidator {
    public static boolean validate(int id, String name, int age, String address, String role, double salary) {
        if (id <= 0) {
            JOptionPane.showMessageDialog(null, "ID must be positive.");
            return false;
        }
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid name.");
            return false;
        }
        if (age < 18 || age > 65) {
            JOptionPane.showMessageDialog(null, "Age must be between 18 and 65.");
            return false;
        }
        if (address == null || address.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid address.");
            return false;
        }
        if (role == null || role.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid role.");
            return false;
        }
        if (salary < 0) {
            JOptionPane.showMessageDialog(null, "Salary must be non-negative.");
            return false;
        }
        return true;
    }
}

