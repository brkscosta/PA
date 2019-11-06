/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

import dijkstra.model.Airport;
import dijkstra.model.Flight;
import dijkstra.model.FlightPlanner;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brunomnsilva
 */

public class Main {
    
    public static void main(String[] args) {
        
        FlightPlanner planner = new FlightPlanner();
        
        Airport hnl = new Airport("HNL", "Honolulu");
        Airport sfo = new Airport("SFO", "San Francisco");
        Airport lax = new Airport("LAX", "Los Angeles");
        Airport ord = new Airport("ORD", "Chicago");
        Airport dfw = new Airport("DFW", "Dallas");
        Airport lga = new Airport("LGA", "New York");
        Airport pvd = new Airport("PVD", "Providence");
        Airport mia = new Airport("MIA", "Miami");
     

        Flight f1 = new Flight("United", 4665, 2555, 5, 10, 430);
        Flight f2 = new Flight("Delta", 6662, 2555, 5, 15, 430);
        Flight f3 = new Flight("United", 5454, 337, 1, 30, 68);
        Flight f4 = new Flight("American", 9431, 337, 1, 25, 83);
        Flight f5 = new Flight("United", 5269, 1743, 3, 55, 80);
        Flight f6 = new Flight("Virgin", 7356, 1743, 3, 55, 78);
        Flight f7 = new Flight("United", 1099, 1843, 4, 0, 239);
        Flight f8 = new Flight("Alaska", 1390, 1843, 3, 55, 241);
        Flight f9 = new Flight("Spirit", 8003, 1233, 2, 50, 215);
        Flight f10 = new Flight("American", 8057, 1233, 3, 15, 187);
        Flight f11 = new Flight("United", 4385, 802, 2, 35, 70);
        Flight f12 = new Flight("Spirit", 6905, 1387, 4, 5, 118);
        Flight f13 = new Flight("American", 5284, 849, 2, 50, 220);
        Flight f14 = new Flight("United", 8530, 849, 2, 55, 166);
        Flight f15 = new Flight("Frontier", 4076, 1205, 3, 10, 37);
        Flight f16 = new Flight("Frontier", 5720, 1099, 3, 20, 74);
        Flight f17 = new Flight("American", 5316, 1099, 3, 10, 167);
        Flight f18 = new Flight("American", 6870, 1120, 2, 40, 176);
        
        
        planner.addAirport(hnl);
        planner.addAirport(lax);
        planner.addAirport(sfo);
        planner.addAirport(ord);
        planner.addAirport(dfw);
        planner.addAirport(lga);
        planner.addAirport(pvd);
        planner.addAirport(mia);
        
        planner.addFlight(hnl, lax, f1);
        planner.addFlight(hnl, lax, f2);
        planner.addFlight(lax, sfo, f3);
        planner.addFlight(lax, sfo, f4);
        planner.addFlight(lax, ord, f5);
        planner.addFlight(lax, ord, f6);
        planner.addFlight(sfo, ord, f7);
        planner.addFlight(sfo, ord, f8);
        planner.addFlight(lax, dfw, f9);
        planner.addFlight(lax, dfw, f10);
        planner.addFlight(ord, dfw, f11);
        planner.addFlight(dfw, lga, f12);
        planner.addFlight(ord, pvd, f13);
        planner.addFlight(ord, pvd, f14);
        planner.addFlight(mia, pvd, f15);        
        planner.addFlight(lga, mia, f16);
        planner.addFlight(lga, mia, f17);
        planner.addFlight(dfw, mia, f18);
        
        
        /* NIVEL 1 */
        List<Flight> flightsLaxOrd = planner.getFlightsBetween(lax, ord);
        System.out.println("Flights between LAX / ORD: " + flightsLaxOrd.toString());
        
        List<Flight> flightsLaxPvd = planner.getFlightsBetween(lax, pvd);
        System.out.println("Flights between LAX / PVD: " + flightsLaxPvd.toString());
        
        /* NIVEL 2 */
        System.out.println(planner);
        
        /* RESTANTES */
        testMinimumCostPath(planner, FlightPlanner.Criteria.TIME, hnl, pvd);
        testMinimumCostPath(planner, FlightPlanner.Criteria.DISTANCE, lax, lga);
        testMinimumCostPath(planner, FlightPlanner.Criteria.COST, sfo, lga);
        testMinimumCostPath(planner, FlightPlanner.Criteria.TIME, sfo, lga);
        
        
    }
    
    /* NIVEL 3/4 */
//    private static void testMinimumCostPath(FlightPlanner planner,
//            FlightPlanner.Criteria criteria, 
//            Airport orig, Airport dest) {
//        
//        List<Airport> pathAirports = new ArrayList<>();
//        
//        System.out.println( String.format("Best (%s) route between %s and %s", criteria, orig, dest));
//        int res = planner.minimumCostPath(criteria, orig, dest, pathAirports);
//        System.out.println( String.format("Total cost (%s) = %d", criteria.getUnit(), res));
//        System.out.println("Airports: " + pathAirports);
//        System.out.println("");
//    }
    
    /* NIVEL 5 */
    private static void testMinimumCostPath(FlightPlanner planner,
            FlightPlanner.Criteria criteria, 
            Airport orig, Airport dest) {
        
        List<Airport> pathAirports = new ArrayList<>();
        List<Flight> pathFlights = new ArrayList<>();
        
        System.out.println( String.format("Best (%s) route between %s and %s", criteria, orig, dest));
        int res = planner.minimumCostPath(criteria, orig, dest, pathAirports, pathFlights);
        System.out.println( String.format("Total cost (%s) = %d", criteria.getUnit(), res));
        System.out.println("Airports: " + pathAirports);
        System.out.println("Flights: " + pathFlights);
        System.out.println("");
    }
}