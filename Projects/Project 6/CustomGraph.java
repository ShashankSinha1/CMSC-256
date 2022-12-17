/*******************************************************************************
 *Shashank Sinha
 * Project 6
 * Taking in a file and its data and creating/interpreting a graph from its data
 *******************************************************************************/

package cmsc256;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class CustomGraph<V> extends UnweightedGraph<V> {

    public CustomGraph<V> readFile(String fileName) throws FileNotFoundException {
        int numberOfVertices = 0;
        List<Edge> edges = new ArrayList<Edge>();

        try {
            File file = new File(fileName);
            Scanner scr = new Scanner(file);

            String[] value = new String[0];

            numberOfVertices = Integer.parseInt(scr.nextLine());

            while (scr.hasNextInt()) {
                value = scr.nextLine().split(" ");
                try {
                    for (int i = 1; i < value.length; i++) {
                        Edge newEdge = new Edge(Integer.parseInt(value[0]), Integer.parseInt(value[i]));
                        edges.add(newEdge);
                    }
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Vertex value invalid");
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");
        }


        return new CustomGraph<V>(edges, numberOfVertices);
    }

    public boolean isComplete() {

        int numberOfVertices = vertices.size();

        if((numberOfVertices * (numberOfVertices - 1) / 2) > dfs(0).getNumberOfVerticesFound()){
            return false;
        }
        return true;
    }

    public boolean areAdjacent(V v1, V v2) {
        int v1Index = getIndex(v1);

        List<Integer> v1Neighbor = getNeighbors(v1Index);

        for(int i = 0; i < v1Neighbor.size(); i++){
            if(v1Neighbor.get(i) == v2){
                return true;
            }
        }
        return false;
    }

    public boolean isConnected() {
        if (vertices.size() < 2) {
            return false;
        }

        return dfs(0).getNumberOfVerticesFound() == vertices.size();
    }

    public List<V> getShortestPath(V begin, V end) {
        List<V> distance = bfs((int) end).getPath((int) begin);
        if (end != distance.get(distance.size()-1)) {
            return null;
        }
        return distance;
    }

    public boolean hasCycle() {
        List<V> listVertices = new ArrayList<>();
        boolean[] visitedVertex = new boolean[vertices.size()];
        int[] parent = new int[vertices.size()];

        for(int i = 0; i < vertices.size(); i++){
            visitedVertex[i] = false;
            listVertices.add(vertices.get(i));
            parent[i] = dfs(i).getParent(i);
        }

        for(int i = 0; i < vertices.size(); i++){
            if(!visitedVertex[i]){
                return true;
            }
        }
        return false;
    }


    public CustomGraph() {
        super();
    }

    public CustomGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    public CustomGraph(List<V> vertices, List<Edge> edges) {
        super(vertices, edges);
    }
    public CustomGraph(List<Edge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public CustomGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

}
