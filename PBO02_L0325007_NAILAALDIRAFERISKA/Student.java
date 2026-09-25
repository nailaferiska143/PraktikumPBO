/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PBO02_L0325007_NAILAALDIRAFERISKA;

/**
 *
 * @author aldir
 */
public class Student {
    String name;
    double score;
    boolean passed;
    
    public Student(String name, double score) {
        this.name = name;
        this.score = score;
        this.passed = false; 
    }
    
    public void checkPassed() {
        if (this.score >= 70) {
            this.passed = true;
        } else {
            this.passed = false;
        }
    }
}
