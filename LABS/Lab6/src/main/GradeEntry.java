/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;

/**
 *
 * @author BRKsCosta
 */
public class GradeEntry implements Serializable {
    
    private String studentCode;
    private String studentName;
    private int studentGrade;

    public GradeEntry(String studentCode, String studentName, int studentGrade) {
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.studentGrade = studentGrade;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getStudentGrade() {
        return studentGrade;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentGrade(int studentGrade) {
        this.studentGrade = studentGrade;
    }

    @Override
    public String toString() {
        return "GradeEntry{" + "studentCode=" + studentCode + ", studentName=" + studentName + ", studentGrade=" + studentGrade + '}' + "\n";
    }
    
}
