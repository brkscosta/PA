/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupmanager.model;

/**
 *
 * @author Patricia Mcedo@PA_2019/20
 */


public class Student {
  
   
    private String name;
    private int id;
    private int classGrade;
   

    public Student(String name, int id, int classGrade) {
        this.name = name;
        this.id = id;
        this.classGrade = classGrade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassGrade() {
        return classGrade;
    }

    public void setClassGrade(int classGrade) {
        this.classGrade = classGrade;
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", id=" + id + ", classGrade=" + classGrade + '}';
    }
    
    
    
}
