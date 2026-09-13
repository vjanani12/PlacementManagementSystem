package org.placement.cms.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.placement.cms.exception.StudentNotFoundException;
import org.placement.cms.model.Student;
import org.placement.cms.util.DBConnection;

public class StudentRepositoryImpl {

    
    public boolean addStudent(Student student) {
        boolean isSuccess = false;
        String query = "INSERT INTO student_placement (student_id, student_name, department, cgpa, status) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, student.getStudentId());
            pstmt.setString(2, student.getStudentName());
            pstmt.setString(3, student.getDepartment());
            pstmt.setDouble(4, student.getCgpa());
            pstmt.setString(5, student.getStatus());
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    //  Search the Student ID (if not throw Exception)
    public Student getStudentById(int studentId) throws StudentNotFoundException {
        Student student = null;
        String query = "SELECT * FROM student_placement WHERE student_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setStudentName(rs.getString("student_name"));
                student.setDepartment(rs.getString("department"));
                student.setCgpa(rs.getDouble("cgpa"));
                student.setStatus(rs.getString("status"));
            } else {
                // "Student ID not found
                throw new StudentNotFoundException("Error: Student ID " + studentId + " Not Found");
            }
        } catch (StudentNotFoundException e) {
            throw e; 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }
 // View All Students 
    public java.util.List<Student> getAllStudents() {
        java.util.List<Student> list = new java.util.ArrayList<>();
        String query = "SELECT * FROM student_placement";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Student s = new Student(
                    rs.getInt("student_id"),
                    rs.getString("student_name"),
                    rs.getString("department"),
                    rs.getDouble("cgpa"),
                    rs.getString("status")
                );
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Update Student Details 
    public boolean updateStudent(int id, double newCgpa, String newStatus) {
        boolean isUpdated = false;
        String query = "UPDATE student_placement SET cgpa = ?, status = ? WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setDouble(1, newCgpa);
            pstmt.setString(2, newStatus);
            pstmt.setInt(3, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) isUpdated = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    //  Delete Student Record
    public boolean deleteStudent(int id) {
        boolean isDeleted = false;
        String query = "DELETE FROM student_placement WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) isDeleted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleted;
    }
    
    public java.util.List<Student> getStudentsByDepartment(String dept) {
        java.util.List<Student> list = new java.util.ArrayList<>();
        String query = "SELECT * FROM student_placement WHERE department = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, dept);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Student s = new Student(
                    rs.getInt("student_id"),
                    rs.getString("student_name"),
                    rs.getString("department"),
                    rs.getDouble("cgpa"),
                    rs.getString("status")
                );
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
}