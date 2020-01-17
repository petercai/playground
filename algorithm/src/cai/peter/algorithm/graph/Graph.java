package cai.peter.algorithm.graph;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.junit.Test;

import java.util.*;

@Data
@RequiredArgsConstructor
public class Graph {
  private Map<Vertex, List<Vertex>> edges = new HashMap<>();

  public void addEdge(Vertex a, Vertex b) {
    if (!edges.containsKey(a)) edges.put(a, null);

    if (!edges.containsKey(b)) edges.put(b, null);

    _add(a, b);
    _add(b, a);
  }

  private void _add(Vertex a, Vertex b) {
    List<Vertex> neighbor = edges.get(a); // get all edges for vertex a
    if (neighbor != null) edges.remove(b); // remove current edge if exist
    else neighbor = new LinkedList<>();

    neighbor.add(b); // add edge
    edges.put(a, neighbor); // update edge info for vertex a
  }

  public boolean hasEdge(Vertex a, Vertex b) {
    return edges.containsKey(a) && edges.get(a).contains(b);
  }

  public void breachFirstSearch(Vertex node) {
    if (node == null) return;

    Queue<Vertex> q = new LinkedList<>();
    q.offer(node);

    while (!q.isEmpty()) {
      Vertex c = q.poll();
      if (c.isVisited()) continue;

      System.out.print(c.visit() + "->");
      List<Vertex> neighbor = edges.get(c);
      if (neighbor == null) continue;
      for (Vertex v : neighbor) {
        if (!v.isVisited()) q.offer(v);
      }
    }
    System.out.println();
  }

  @Test
  public void testBFS() {
    Graph g = new Graph();
    Vertex a = new Vertex(0, "A");
    Vertex b = new Vertex(1, "B");
    Vertex c = new Vertex(2, "C");
    Vertex d = new Vertex(3, "D");
    Vertex e = new Vertex(4, "E");

    g.addEdge(a, d);
    g.addEdge(a, b);
    g.addEdge(a, c);
    g.addEdge(c, d);

    g.breachFirstSearch(b);
  }

  public void depthFirstSearch(Vertex node) {
    node.visit();
    System.out.print(node + "->");
    List<Vertex> neighbors = edges.get(node);
    if (neighbors == null) return;
    for (Vertex v : neighbors) {
      if (!v.isVisited()) depthFirstSearch(v);
    }
  }

  public void depthFirstSearchStack(Vertex node ){
    //TODO:
  }

  public void depthFristSearchAggresive(Vertex node){
    depthFirstSearch(node);
    for(Vertex c : edges.keySet()){
      if(!c.isVisited()) depthFirstSearch(c);
    }
  }
}
