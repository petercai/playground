package cai.peter.algorithm.graph;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.junit.Test;

import java.util.*;

@Data
@RequiredArgsConstructor
public class Graph {
  private Map<Vertex, List<Vertex>> map = new HashMap<>();

  public void addEdge(Vertex a, Vertex b) {
    if (!map.containsKey(a)) map.put(a, null);

    if (!map.containsKey(b)) map.put(b, null);

    _add(a, b);
    _add(b, a);
  }

  private void _add(Vertex a, Vertex b) {
    List<Vertex> neighbor = map.get(a); // get all edges for vertex a
    if (neighbor != null) map.remove(b); // remove current edge if exist
    else neighbor = new LinkedList<>();

    neighbor.add(b); // add edge
    map.put(a, neighbor); // update edge info for vertex a
  }

  public boolean hasEdge(Vertex a, Vertex b) {
    return map.containsKey(a) && map.get(a).contains(b);
  }

  public void breachFirstSearch(Vertex node) {
    if (node == null) return;

    Queue<Vertex> q = new LinkedList<>();
    q.offer(node);

    while (!q.isEmpty()) {
      Vertex c = q.poll();
      if (c.isVisited()) continue;

      System.out.print(c.visit() + "->");
      List<Vertex> neighbor = map.get(c);
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
}
