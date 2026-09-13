package org.placement.cms.model;

	public class Student {
	    private int studentId;
	    private String studentName;
	    private String department;
	    private double cgpa;
	    private String status;

	    // Default Constructor
	    public Student() {}

	    // Parameterized Constructor
	    public Student(int studentId, String studentName, String department, double cgpa, String status) {
	        this.studentId = studentId;
	        this.studentName = studentName;
	        this.department = department;
	        this.cgpa = cgpa;
	        this.status = status;
	    }

	    // Getters and Setters
	    public int getStudentId() { return studentId; }
	    public void setStudentId(int studentId) { this.studentId = studentId; }

	    public String getStudentName() { return studentName; }
	    public void setStudentName(String studentName) { this.studentName = studentName; }

	    public String getDepartment() { return department; }
	    public void setDepartment(String department) { this.department = department; }

	    public double getCgpa() { return cgpa; }
	    public void setCgpa(double cgpa) { this.cgpa = cgpa; }

	    public String getStatus() { return status; }
	    public void setStatus(String status) { this.status = status; }
	}


