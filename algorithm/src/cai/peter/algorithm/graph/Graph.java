package cai.peter.algorithm.graph;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class Graph {
  private Map<Vertex, LinkedList<Vertex>> map=new HashMap<>();

  public void addEdge(Vertex a, Vertex b) {
    if (!map.containsKey(a)) map.put(a, null);

    if (!map.containsKey(b)) map.put(b, null);

    _add(a, b);
    _add(b, a);
  }

  private void _add(Vertex a, Vertex b) {
    LinkedList<Vertex> neighbor = map.get(a); // get all edges for vertex a
    if (neighbor != null) map.remove(b); // remove current edge if exist
    else neighbor = new LinkedList<>();

    neighbor.add(b); // add edge
    map.put(a, neighbor); // update edge info for vertex a
  }

  public boolean hasEdge(Vertex a, Vertex b){
      return map.containsKey(a) && map.get(a).contains(b);
  }
}
