package org.placement.cms.controller;

import java.util.List;
import java.util.Scanner;
import org.placement.cms.exception.StudentNotFoundException;
import org.placement.cms.model.Student;
import org.placement.cms.service.StudentService;

public class StudentController {
    
    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        StudentService service = new StudentService();
        
        while (true) {
            System.out.println("\n--- Placement Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student by ID");
            System.out.println("3. View All Students");
            System.out.println("4. Update Student Details");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            
            // Input validation 
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next(); 
                continue; 
            }
            
            int choice = scanner.nextInt();
                        
            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Student ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        
                        System.out.print("Enter Student Name: ");
                        String name = scanner.nextLine();
                        
                        System.out.print("Enter Department: ");
                        String dept = scanner.nextLine();
                        
                        System.out.print("Enter CGPA: ");
                        double cgpa = scanner.nextDouble();
                        scanner.nextLine();
                        
                        System.out.print("Enter Status (Placed/Not Placed): ");
                        String status = scanner.nextLine();
                        
                        Student student = new Student(id, name, dept, cgpa, status);
                        boolean isAdded = service.registerStudent(student);
                        
                        if (isAdded) {
                            System.out.println("Success: Student details added successfully!");
                        } else {
                            System.out.println("Error: Failed to add student.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input! Please enter correct details.");
                        scanner.nextLine();
                    }
                    break;
                    
                case 2:
                    System.out.print("Enter Student ID to search: ");
                    int searchId = scanner.nextInt();
                    try {
                        Student foundStudent = service.searchStudentById(searchId);
                        System.out.println("\n--- Student Found ---");
                        System.out.println("ID         : " + foundStudent.getStudentId());
                        System.out.println("Name       : " + foundStudent.getStudentName());
                        System.out.println("Department : " + foundStudent.getDepartment());
                        System.out.println("CGPA       : " + foundStudent.getCgpa());
                        System.out.println("Status     : " + foundStudent.getStatus());
                    } catch (StudentNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case 3:
                    List<Student> students = service.getAllStudents();
                    if (students.isEmpty()) {
                        System.out.println("No student records found!");
                    } else {
                        System.out.println("\n--- All Students List ---");
                        for (Student s : students) {
                            System.out.println("ID: " + s.getStudentId() + " | Name: " + s.getStudentName() + " | Dept: " + s.getDepartment() + " | CGPA: " + s.getCgpa() + " | Status: " + s.getStatus());
                        }
                    }
                    break;
                    
                case 4:
                    System.out.print("Enter Student ID to update: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter New CGPA: ");
                    double newCgpa = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter New Status: ");
                    String newStatus = scanner.nextLine();
                    
                    boolean isUpdated = service.updateStudent(updateId, newCgpa, newStatus);
                    if (isUpdated) {
                        System.out.println("Success: Student updated successfully!");
                    } else {
                        System.out.println("Error: Student ID not found or update failed.");
                    }
                    break;
                    
                case 5:
                    System.out.print("Enter Student ID to delete: ");
                    int deleteId = scanner.nextInt();
                    boolean isDeleted = service.deleteStudent(deleteId);
                    if (isDeleted) {
                        System.out.println("Success: Student deleted successfully!");
                    } else {
                        System.out.println("Error: Student ID not found.");
                    }
                    break;
                case 6:
                    scanner.nextLine(); // clear buffer
                    System.out.print("Enter Department to search (eg: CSE, IT): ");
                    String searchDept = scanner.nextLine();
                    
                    List<Student> deptStudents = service.getStudentsByDepartment(searchDept);
                    if (deptStudents.isEmpty()) {
                        System.out.println("No students found in department: " + searchDept);
                    } else {
                        System.out.println("\n--- Students in " + searchDept + " ---");
                        for (Student s : deptStudents) {
                            System.out.println("ID: " + s.getStudentId() + " | Name: " + s.getStudentName() + " | CGPA: " + s.getCgpa() + " | Status: " + s.getStatus());
                        }
                    }
                    break;
                    
                case 7:
                    System.out.println("Thank you! Exiting application...");
                    scanner.close();
                    System.exit(0);
                    
                default:
                    System.out.println("Invalid choice! Please choose between 1 to 6.");
            }
        }
    }
}