/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA_graph;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import PA_application.*;
import PA_graph.*;
import java.util.Iterator;

/*
  * @author BRKsCosta
 */
public class GraphLEdgeListTest {

    Graph<Local, Ponte> graph = new GraphLEdgeList<>();

    public GraphLEdgeListTest() {
    }

    @Before
    public void setUp() {
        Local local1 = new Local("Local 1");
        Local local2 = new Local("Local 2");
        Local local3 = new Local("Local 3");
        Local local4 = new Local("Local 4");
        Local local5 = new Local("Local 5");

        Ponte pt1 = new Ponte("Quinta", 3);
        Ponte pt2 = new Ponte("Almada", 5);
        Ponte pt3 = new Ponte("Lisboa", 8);
        Ponte pt4 = new Ponte("Seixal", 4);
        Ponte pt5 = new Ponte("Barreiro", 7);

        graph.insertVertex(local1);
        graph.insertVertex(local2);
        graph.insertVertex(local3);
        graph.insertVertex(local4);
        graph.insertVertex(local5);

        graph.insertEdge(local1, local2, pt1);
        graph.insertEdge(local1, local3, pt2);
        graph.insertEdge(local3, local4, pt3);
        graph.insertEdge(local3, local5, pt4);

    }

    @Test
    public void testNumVertices() {

        assertEquals("Numero de vertices esperados ", 5, graph.numVertices());
    }

    @Test
    public void testNumEdges() {

        assertEquals("numero de arestas esperadas ", 4, graph.numEdges());
    }

    @Test
    public void testIncidentEdges() {
       
        for (Iterator<Vertex<Local>> it = graph.vertices().iterator(); it.hasNext();) {
            Object args = it.next();
            if (args.equals("Local 1")) {
                System.out.println("Teste incident" + graph.incidentEdges((Vertex<Local>) args));
            }
        }
    }
    
    @Test
    public void testInsertVertex() {
    }

    @Test
    public void testAreAdjacent() {
    }

    @Test
    public void testInsertEdge_3args_1() {
    }

    @Test
    public void testInsertEdge_3args_2() {
    }

    @Test
    public void testRemoveEdge() {
    }

    @Test
    public void testRemoveVertex() {
    }

}
