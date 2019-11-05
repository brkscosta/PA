/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import graph.Edge;
import graph.Vertex;
import graph.Graph;
import graph.GraphEdgeList;
import model.Person;

/**
 *
 * @author BRKsCosta
 */
public class UniversityNetwork {

    private Graph<Person, Relationship> network = new GraphEdgeList<>();

    public UniversityNetwork() {

    }

    private Vertex<Person> findPerson(int id) {

        Iterable<Vertex<Person>> vertices = network.vertices();

        for (Vertex<Person> p : vertices) {
            if (p.element().getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void addPerson(Person person) throws NetworkException {
        if (findPerson(person.getId()) != null) {
            throw new NetworkException("Pessoa já existe!");
        }

        network.insertVertex(person);
    }

    public void addGroupRelationShip(String description, int idStudent, int idStudent2)
            throws NetworkException {

        Vertex<Person> p1 = this.findPerson(idStudent);
        Vertex<Person> p2 = this.findPerson(idStudent2);

        if (p1 == null || p2 == null || !p1.element().isRole(Person.PersonRole.STUDENT)
                || !p2.element().isRole(Person.PersonRole.STUDENT)) {

            throw new NetworkException("Parametros inválidos!");
        }

        network.insertEdge(p1, p2, new Relationship(description, Relationship.RelRole.GROUP));

    }

    public void addClassRelationShip(String description, int teacher, int idStudent)
            throws NetworkException {

        Vertex<Person> p1 = this.findPerson(teacher);
        Vertex<Person> p2 = this.findPerson(idStudent);

        if (p1 == null || p2 == null || !p1.element().isRole(Person.PersonRole.TEACHER)
                || !p2.element().isRole(Person.PersonRole.STUDENT)) {

            throw new NetworkException("Parametros inválidos!");
        }

        network.insertEdge(p1, p2, new Relationship(description, Relationship.RelRole.CLASS));

    }

    public String toStrinTeacherStudents() {

        String teacher = "DOCENTES\n";

        for (Vertex<Person> p : network.vertices()) {
            if (p.element().isRole(Person.PersonRole.TEACHER)) {
                teacher += "DOCENTE " + p.element().getName() + "(" + p.element().getId() + ")\n";

                for (Edge<Relationship, Person> rel : network.edges()) {
                    if (rel.vertices()[0] == p && rel.vertices()[1].element().isRole(Person.PersonRole.STUDENT)) {
                        teacher += "\t de Aluno" + rel.vertices()[1].element().
                                getName() + "(" + rel.vertices()[1].element().getId() + ")\n";
                    }
                }
            }
        }
        return teacher;
    }

}
