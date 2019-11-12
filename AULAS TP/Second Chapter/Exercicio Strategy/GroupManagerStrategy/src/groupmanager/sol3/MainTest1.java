/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupmanager.sol3;

import groupmanager.model.Student;
import java.util.HashSet;

/**
 *
 * @author Utilizador
 */
public class MainTest1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HashSet<Student> students = new HashSet();
        fillStudents(students);
        GroupManagement groupM = new GroupManagementRandom(students);
        System.out.println(groupM.createGrupo(5));

    }

    private static void fillStudents(HashSet<Student> students) {
        students.add(new Student("Ana", 1, 5));
        students.add(new Student("Ana Maria", 3, 5));
        students.add(new Student("Anita", 5, 5));
        students.add(new Student("Riana", 7, 5));
        students.add(new Student("Biana", 8, 6));
        students.add(new Student("Tiana", 9, 6));
        students.add(new Student("Dia", 14, 6));
        students.add(new Student("Liliana", 13, 7));
        students.add(new Student("Susana", 12, 7));
        students.add(new Student("Mariana", 11, 7));
        students.add(new Student("Anabela", 10, 8));

    }
}
