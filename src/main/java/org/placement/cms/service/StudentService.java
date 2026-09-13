package org.placement.cms.service;

import org.placement.cms.exception.StudentNotFoundException;
import org.placement.cms.model.Student;
import org.placement.cms.repository.StudentRepositoryImpl;

public class StudentService {
    
    private StudentRepositoryImpl repo = new StudentRepositoryImpl();

   
    public boolean registerStudent(Student student) {
        return repo.addStudent(student);
    }


    public Student searchStudentById(int studentId) throws StudentNotFoundException {
        Student student = repo.getStudentById(studentId);
        if (student == null) {
            throw new StudentNotFoundException("Student ID " + studentId + " not found!");
        }
        return student;
    }
    public java.util.List<Student> getAllStudents() {
        return repo.getAllStudents();
    }
    public java.util.List<Student> getStudentsByDepartment(String dept) {
        return repo.getStudentsByDepartment(dept);
    }

    public boolean updateStudent(int id, double cgpa, String status) {
        return repo.updateStudent(id, cgpa, status);
    }

    public boolean deleteStudent(int id) {
        return repo.deleteStudent(id);
    }
}
