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


    public int getNumberOfStudents(Person p) {

        Vertex<Person> person = findPerson(p.getId());
        Iterable<Edge<Relationship, Person>> relations = network.incidentEdges(person);
        int count = 0;
        for (Edge<Relationship, Person> relation : relations) {
            count++;
        }
        return count;
    }

    public Person getMostPopular(int id1, int id2) {

        Vertex<Person> p1 = this.findPerson(id1);
        Vertex<Person> p2 = this.findPerson(id2);
        int count = 0;
        int pp1 = 0, pp2 = 0;
        Person people1 = null, people2 = null;
        for (Vertex<Person> p : network.vertices()) {
            if (p.equals(p1)) {
                for (Edge<Relationship, Person> edge : network.edges()) {
                    if (edge.vertices()[1] == p || edge.vertices()[0] == p && edge.element().isRole(Relationship.RelRole.GROUP)
                            || edge.element().isRole(Relationship.RelRole.CLASS)) {
                        count++;
                        pp1 = count;
                        people1 = p.element();
                    }
                }
            } else if (p.equals(p2)) {
                for (Edge<Relationship, Person> edge : network.edges()) {
                    if (edge.vertices()[1] == p || edge.vertices()[0] == p && edge.element().isRole(Relationship.RelRole.GROUP)
                            || edge.element().isRole(Relationship.RelRole.CLASS)) {
                        count++;
                        pp2 = count;
                        people2 = p.element();
                    }
                }
            }
        }

        if (pp1 > pp2) {
            return people1;
        }

        return people2;
    }

    public void removeRelation(int id, int id2) throws NetworkException {

        Vertex<Person> p1 = findPerson(id);
        Vertex<Person> p2 = findPerson(id2);

        //System.out.println(p1);
        //System.out.println(p2);

        if (!network.areAdjacent(p1, p2)) {
            throw new NetworkException("Não há relações");
        }
        for (Edge<Relationship, Person> edge : network.edges()) {
            if (network.incidentEdges(p1) == network.incidentEdges(p2)) {
                System.out.println(edge);
            }
        }
    }


}
