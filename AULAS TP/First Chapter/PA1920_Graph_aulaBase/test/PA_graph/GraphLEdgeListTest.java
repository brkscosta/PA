/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA_graph;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author BRKsCosta
 */
public class GraphLEdgeListTest {
    
    Graph<String, String> graph = new GraphLEdgeList<>();
    
    public GraphLEdgeListTest() {
    }
    
    @Before
    public void setUp() {
    
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        
        graph.insertEdge("A", "B", "e1");
        
    }

    @Test
    public void testNumVertices() {
    }

    @Test
    public void testNumEdges() {
    }

    @Test
    public void testVertices() {
    }

    @Test
    public void testEdges() {
    }

    @Test
    public void testIncidentEdges() {
    }

    @Test
    public void testOpposite() {
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
