/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Exceptions.InvalidVertexException;
import Interfaces.Digraph;
import Interfaces.Edge;
import Interfaces.Vertex;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 *
 * @author BRKsCosta
 */
public class MyDiGraphTest {

    private final Digraph<String, String> digraph;
    private final List<Vertex<String>> vertex;
    private final List<Edge<String, String>> edges;

    public MyDiGraphTest() {
        this.digraph = new MyDiGraph<>();
        this.vertex = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    @Before
    public void setUp() {

        Vertex<String> google = digraph.insertVertex("Google");
        Vertex<String> facebook = digraph.insertVertex("Facebook");
        Vertex<String> instagram = digraph.insertVertex("Instagram");
        Vertex<String> santander = digraph.insertVertex("Santander");
        Vertex<String> moodle = digraph.insertVertex("Moodle");

        vertex.add(google);
        vertex.add(facebook);
        vertex.add(instagram);
        vertex.add(santander);

        String linkFacebook = "facebook.com";
        String linkMoodle = "moodle.com";
        String linkInstagram = "instagram.com";
        String linkSantander = "santander.com";

        Edge<String, String> e1 = digraph.insertEdge(google, facebook, linkFacebook);
        edges.add(e1);
        edges.add(digraph.insertEdge(google, moodle, linkMoodle));
        edges.add(digraph.insertEdge(facebook, instagram, linkInstagram));
        edges.add(digraph.insertEdge(facebook, santander, linkSantander));

    }

    /**
     * Test of numVertices method, of class MyDiGraph.
     */
    @Test
    public void testNumVertices() {
        assertEquals(5, digraph.numVertices());
    }

    /**
     * Test of numEdges method, of class MyDiGraph.
     */
    @Test
    public void testNumEdges() {
        assertEquals(4, digraph.numEdges());
    }

    /**
     * Test of vertices method, of class MyDiGraph.
     */
    @Test
    public void testVertices() {

//        Collection<Vertex<String>> expVertex = this.vertex;
//        Collection<Vertex<String>> resVertex = digraph.vertices();
//
//        assertEquals(expVertex, resVertex);
    }
    
    /**
     * Test of incidentEdges method, of class MyDiGraph.
     */
    @Test
    public void testIncidentEdges() {

    }

    /**
     * Test of opposite method, of class MyDiGraph.
     */
    @Test
    public void testOpposite() {
    }

    /**
     * Test of areAdjacent method, of class MyDiGraph.
     */
    @Test
    public void testAreAdjacent() {
    }

    /**
     * Test of insertVertex method, of class MyDiGraph.
     */
    @Test
    public void testInsertVertex() {
    }

    /**
     * Test of insertEdge method, of class MyDiGraph.
     */
    @Test(expected = InvalidVertexException.class)
    public void testInsertEdge_vertex_no_exists() {
        Edge<String, String> e1 = digraph.insertEdge("Google", "Ana Aeroports", "ana.com");

        assertEquals("ana.com", e1);
    }

    /**
     * Test of insertEdge method, of class MyDiGraph.
     */
    @Test(expected = InvalidVertexException.class)
    public void testInsertEdge_vertex_alredy_exists() {
        Vertex<String> v = digraph.insertVertex(vertex.get(0).element());

        assertEquals("Google", v);
    }

    public void teste_insert_vertex() {
        assertEquals("Asus", digraph.insertVertex("Asus"));
    }

    /**
     * Test of removeVertex method, of class MyDiGraph.
     */
    @Test
    public void testRemoveVertex() {

        for (Vertex<String> vertex1 : vertex) {
            if (vertex1.element().contains("Moodle")) {
                assertEquals("Moodle", digraph.removeVertex(vertex1));
            }
        }
    }

    /**
     * Test of removeEdge method, of class MyDiGraph.
     */
    @Test
    public void testRemoveEdge() {

        for (Edge<String, String> edge : edges) {

            if (edge.element().contains("facebook.com")) {
                assertEquals("facebook.com", digraph.removeEdge(edge));
            }
        }

    }

    /**
     * Test of replace method, of class MyDiGraph.
     */
    @Test
    public void testReplace_Vertex() {

        String retornado = "";

        for (Vertex<String> vertex1 : vertex) {

            if (vertex1.element().contains("Google")) {
                retornado = digraph.replace(vertex1, "Amazon");
            }
        }

        assertEquals("Google", retornado);
    }

    /**
     * Test of replace method, of class MyDiGraph.
     */
    @Test
    public void testReplace_Edge() {
        String retornado = "";
        for (Edge<String, String> edge : digraph.edges()) {
            if (edge.element().contains("facebook.com")) {
                retornado = digraph.replace(edge, "amazon.com");
            }
        }
        assertEquals("facebook.com", retornado);
    }

    /**
     * Test of outboundEdges method, of class MyDiGraph.
     */
    @Test
    public void testOutboundEdges() {
        Collection<Edge<String, String>> outboundEdges = digraph.outboundEdges(vertex.get(0));

        List<String> arr = new ArrayList<>();
        List<String> arrAux = new ArrayList<>();
        
        arrAux.add("facebook.com");
        arrAux.add("moodle.com");
        
        for (Edge<String, String> outboundEdge : outboundEdges) {
            if (outboundEdge.vertices()[0] == vertex.get(0)) {
                arr.add(outboundEdge.element());
            }
        }
        System.out.println(" " + arr.contains("facebook.com") + " " + arrAux.contains("moodle.com"));
        System.out.println(" " + arr.contains("moodle.com") + " " + arrAux.contains("facebook.com"));
        
    }

}
