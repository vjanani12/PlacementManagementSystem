package org.placement.cms.main;

import org.placement.cms.controller.StudentController;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("Starting Placement Management System...");
        
       
        StudentController controller = new StudentController();
        controller.startMenu();
    }
}