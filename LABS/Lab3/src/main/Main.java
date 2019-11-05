/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import graph.Vertex;
import model.Person;
import model.Relationship;
import model.UniversityNetwork;

/**
 *
 * @author BRKsCosta
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        UniversityNetwork network = new UniversityNetwork();
        
        network.addPerson(new Person(135, "Rodrigo", Person.PersonRole.STUDENT));
        network.addPerson(new Person(31, "Ana", Person.PersonRole.TEACHER));
        network.addPerson(new Person(2, "Ana", Person.PersonRole.STUDENT));
        network.addPerson(new Person(215, "Cátia", Person.PersonRole.STUDENT));
        network.addPerson(new Person(131, "Pedro", Person.PersonRole.STUDENT));
        network.addPerson(new Person(35, "Pedro", Person.PersonRole.TEACHER));
        network.addPerson(new Person(235, "João", Person.PersonRole.STUDENT));
        network.addPerson(new Person(18, "Aberto", Person.PersonRole.STUDENT));
        network.addPerson(new Person(231, "Rita", Person.PersonRole.STUDENT));
        
        //Classes professores com alunos
        network.addClassRelationShip("Análise Matemática", 31, 18);
        network.addClassRelationShip("Análise Matemática", 31, 131);
        network.addClassRelationShip("Alegra Geral", 31, 135);
        network.addClassRelationShip("Alegra Geral", 31, 215);
        network.addClassRelationShip("PA", 35, 18);
        network.addClassRelationShip("PA", 35, 131);
        network.addClassRelationShip("PA", 35, 235);
        
        //Grupos
        network.addGroupRelationShip("Colega de Grupo Algebra 1", 215, 135);
        network.addGroupRelationShip("Colegas de Turma", 18, 231);
        network.addGroupRelationShip("Colegas de Grupo PA-1", 131, 235);
        
        System.out.println(network.toStrinTeacherStudents());
        
        
    }
    
}
