/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra.model;

import dijkstra.graph.Edge;
import dijkstra.graph.Graph;
import dijkstra.graph.GraphEdgeList;
import dijkstra.graph.InvalidVertexException;
import dijkstra.graph.Vertex;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author brunomnsilva
 */
public class FlightPlanner {

    public enum Criteria {
        DISTANCE,
        TIME,
        COST;

        public String getUnit() {
            switch (this) {
                case COST:
                    return "€";
                case TIME:
                    return "Minutes";
                case DISTANCE:
                    return "Miles";
            }
            return "Unknown";
        }
    };

    private final Graph<Airport, Flight> graph;

    public FlightPlanner() {
        this.graph = new GraphEdgeList<>();
    }

    private Vertex<Airport> checkAirport(Airport airport) throws FlightPlannerException {
        if (airport == null) {
            throw new FlightPlannerException("Airport cannot be null");
        }

        Vertex<Airport> find = null;
        for (Vertex<Airport> v : graph.vertices()) {
            if (v.element().equals(airport)) { //equals was overriden in Airport!!
                find = v;
            }
        }

        if (find == null) {
            throw new FlightPlannerException("Airport with code (" + airport.getCode() + ") does not exist");
        }

        return find;
    }

    public void addAirport(Airport airport) throws FlightPlannerException {

        if (airport == null) {
            throw new FlightPlannerException("Airport cannot be null");
        }

        try {
            graph.insertVertex(airport);
        } catch (InvalidVertexException e) {
            throw new FlightPlannerException("Airport with code (" + airport.getCode() + ") already exists");

        }
    }

    public void addFlight(Airport airport1, Airport airport2, Flight flight)
            throws FlightPlannerException {

        if (flight == null) {
            throw new FlightPlannerException("Flight is null");
        }

        Vertex<Airport> a1 = checkAirport(airport1);
        Vertex<Airport> a2 = checkAirport(airport2);

        try {
            graph.insertEdge(a1, a2, flight);
        } catch (InvalidVertexException e) {
            throw new FlightPlannerException("The flight (" + flight.getDescription() + ") already exists");
        }
    }

    public List<Flight> getFlightsBetween(Airport airport1, Airport airport2)
            throws FlightPlannerException {

        if (airport1 == null || airport2 == null) {
            throw new FlightPlannerException("Airport cannot be null");
        }

        Vertex<Airport> a1 = checkAirport(airport1);
        Vertex<Airport> a2 = checkAirport(airport2);

        List<Flight> flights = new ArrayList<>();

        try {
            for (Edge<Flight, Airport> edge : graph.edges()) {
                if (edge.vertices()[0] == a1 && edge.vertices()[1] == a2
                        || edge.vertices()[1] == a2 && edge.vertices()[0] == a1) {
                    Flight element = edge.element();
                    flights.add(element);

                }
            }
            return flights;
        } catch (InvalidVertexException e) {
            throw new FlightPlannerException(e.getMessage());
        }

    }

    @Override
    public String toString() {

        String output = "";

        output += "FLIGHT PLANER (" + this.graph.numVertices() + " airports | " + this.graph.numEdges() + " flights) \n";

        for (Vertex<Airport> v : this.graph.vertices()) {
            for (Vertex<Airport> vOther : this.graph.vertices()) {
                if (vOther != v) {
                    List<Flight> list = this.getFlightsBetween(v.element(), vOther.element());

                    output += "\t" + v.element().getFullName() + "(" + v.element().getCode() + ") TO " + vOther.element().getFullName() + "(" + vOther.element().getCode() + ")\n";

                    if (list.size() == 0) {
                        output += "\t\t(no flights) \n";
                    } else {
                        for (Flight f : list) {
                            output += "\t\t " + f + "\n";
                        }
                    }
                    output += "\n";
                }
            }
        }

        return output;
    }

    public int minimumCostPath(Criteria criteria, Airport orig, Airport dst,
            List<Airport> airports, List<Flight> pathFlights)
            throws FlightPlannerException {

        HashMap<Vertex<Airport>, Vertex<Airport>> parents = new HashMap();
        HashMap<Vertex<Airport>, Double> distances = new HashMap();
        HashMap< Vertex<Airport>, Edge<Flight, Airport>> newFlights = new HashMap();

        Vertex<Airport> origVer = checkAirport(orig);
        airports.clear();
        dijkstra(criteria, origVer, distances, parents, newFlights);
        Vertex<Airport> v = checkAirport(dst);
        double cost = distances.get(v);
        airports.add(0, orig);

        do {
            airports.add(1, v.element());
            v = parents.get(v);
        } while (v != origVer);

        for (Airport a : airports) {
            Vertex<Airport> newAirport = checkAirport(a);
            if (newFlights.get(newAirport) != null) {
                pathFlights.add(newFlights.get(newAirport).element());
            }
        }

        return (int) cost;
    }

    private void dijkstra(Criteria criteria, Vertex<Airport> orig,
            Map<Vertex<Airport>, Double> costs,
            Map<Vertex<Airport>, Vertex<Airport>> predecessors,
            Map<Vertex<Airport>, Edge<Flight, Airport>> flights) {

        List<Vertex<Airport>> unvisited = new ArrayList<>();
        for (Vertex<Airport> airport : graph.vertices()) {
            unvisited.add(airport);
            costs.put(airport, Double.MAX_VALUE);
            predecessors.put(airport, null);
        }
        costs.put(orig, 0.0);
        while (!unvisited.isEmpty()) {
            Vertex<Airport> lowCostVert = findLowerCostVertex(unvisited, costs);
            unvisited.remove(lowCostVert);
            for (Edge<Flight, Airport> edge : graph.incidentEdges(lowCostVert)) {
                Vertex<Airport> opposite = graph.opposite(lowCostVert, edge);
                if (unvisited.contains(opposite)) {
                    double flightCost = 0;
                    switch (criteria) {
                        case COST:
                            flightCost = edge.element().getPriceEuros();
                            break;
                        case DISTANCE:
                            flightCost = edge.element().getDistanceMiles();
                            break;
                        case TIME:
                            flightCost = edge.element().getDurationMinutes();
                            break;
                    }
                    double dist = costs.get(lowCostVert) + flightCost;
                    if (costs.get(opposite) > dist) {
                        costs.put(opposite, dist);
                        flights.put(opposite, edge);
                        predecessors.put(opposite, lowCostVert);
                    }
                }
            }
        }

    }

    private Vertex<Airport> findLowerCostVertex(List<Vertex<Airport>> unvisited,
            Map<Vertex<Airport>, Double> costs) {

        double min = Double.MAX_VALUE;
        Vertex<Airport> minCostVertex = null;
        for (Vertex<Airport> airport : unvisited) {
            if (costs.get(airport) <= min) {
                minCostVertex = airport;
                min = costs.get(airport);
            }
        }
        return minCostVertex;
    }

}
