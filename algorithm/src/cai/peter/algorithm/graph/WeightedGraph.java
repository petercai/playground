package cai.peter.algorithm.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WeightedGraph {
    private Set<Vertex> vertices = new HashSet<>();

    public void addNeighbor(Vertex... neighbors){
        vertices.addAll(Arrays.asList(neighbors));
    }

}
