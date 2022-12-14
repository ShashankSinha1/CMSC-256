package cmsc256;

import bridges.base.GraphAdjListSimple;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.EarthquakeUSGS;

import java.util.ArrayList;
import java.util.List;

public class GraphEarthquakeData {
    public static double calcDistance(double latitude1, double longitude1, double
            latitude2, double longitude2) {
        final int radius = 6371; // Radius of the earth in km
// Haversine formula to calculate a value between 0 and 1 between points on a sphere,
//  1 being the opposite side of the sphere
        double laDistance = Math.toRadians(latitude2 - latitude1);
        double loDistance = Math.toRadians(longitude2 - longitude1);
        double a = Math.sin(laDistance / 2) * Math.sin(laDistance / 2)
                + Math.cos(Math.toRadians(latitude1)) *
                Math.cos(Math.toRadians(latitude2))
                * Math.sin(loDistance / 2) * Math.sin(loDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = radius * c;    //convert to km
        return distance;
    }
    public static void main(String[] args) throws Exception {
// Create a Bridges object
        Bridges bridges = new Bridges(13, "shashanksinha", "920515443262");
// Get a DataSource object from Bridges
        DataSource ds = bridges.getDataSource();
        // Set an assignment title
        bridges.setTitle("Earthquake Data Graph Lab");
        bridges.setDescription("CMSC 256, Fall 2022");
// Initialize a Graph
        GraphAdjListSimple<String> graph = new GraphAdjListSimple<>();

        /* TODO:
         * Grab Earthquake data and store it in a List
         * Sort the list by magnitude
         * Retain only 10 earthquakes of highest magnitude
         */

        ArrayList<EarthquakeUSGS> largestEarthquakes = new ArrayList<>(10);
        List<EarthquakeUSGS> eq_list = ds.getEarthquakeUSGSData(5000);
        double curr = 0;
        EarthquakeUSGS largestValue = null;

        for (int i = 0; i <= 10; i++){
            for (EarthquakeUSGS eq : eq_list){
                if (eq.getMagnitude() >= curr){
                    largestValue = eq;
                    curr = eq.getMagnitude();
                }
            }
            largestEarthquakes.add(largestValue);
            eq_list.remove(largestValue);
            curr = 0;
        }

    /* TODO:
    * Add the Earthquakes to the graph
    * Set each earthquake's location based on its latitude and longitude
    * ex: graph.getVisualizer(key).setLocation(earthquake.getLongit(),
earthquake.getLatit());
    * Tweak the colors or other visual elements if you wish; For example, if the
magnitude is higher than 6, set the color to red
    */

        for (EarthquakeUSGS eq: largestEarthquakes){
            graph.addVertex(eq.getTitle(), eq.getTitle());
            graph.getVisualizer(eq.getTitle()).setLocation(eq.getLongit(), eq.getLatit());
            if(eq.getMagnitude() > 6){
                graph.getVisualizer(eq.getTitle()).setColor("red");
            }
        }


        bridges.setCoordSystemType("equirectangular");
        bridges.setDataStructure(graph);
        bridges.setMapOverlay(true);
        bridges.setMap("world", "all");
        bridges.setTitle("Earthquake Map");
        bridges.visualize();
        /* TODO:
         * Compare the distances between all vertexes in the graph, drawing an edge
         * if they are within 500km. A method is provided to give a rough
         * estimate between 2 lat,long points.
         *
         * example usage: calcDistance(eq1.getLatit(), eq1.getLongit(),
         *                eq2.getLatit(), eq2.getLongit());
         * which returns a double representing the distance of two points in km
         */

        for(int i =0; i < largestEarthquakes.size(); i++){
            for (int j = 1; j < i; j++){
                double distance = calcDistance(largestEarthquakes.get(i).getLatit(), largestEarthquakes.get(i).getLongit(), largestEarthquakes.get(j).getLatit(), largestEarthquakes.get(j).getLongit());
                if (distance < 500){
                    graph.addEdge(largestEarthquakes.get(i).getTitle(), largestEarthquakes.get(j).getTitle());
                    graph.getLinkVisualizer(largestEarthquakes.get(i).getTitle(), largestEarthquakes.get(j).getTitle()).setColor("green");
                }
            }
        }

        bridges.visualize();
    /* TODO:
    * Reset the locations of the vertices by setting their location to
    * Double.POSITIVE_INFINITY
    *
    * ex: graph.getVisualizer(key).setLocation(Double.POSITIVE_INFINITY,
Double.POSITIVE_INFINITY)
    */

        for(EarthquakeUSGS eq : largestEarthquakes){
            graph.getVisualizer(eq.getTitle()).setLocation(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        }

        bridges.setMapOverlay(false);
        bridges.visualize();
    }
}
