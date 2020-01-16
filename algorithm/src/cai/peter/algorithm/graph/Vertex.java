package cai.peter.algorithm.graph;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Vertex {
  private final int value;
  private final String name;
  private boolean visited;

  public Vertex visit() {
    this.visited = true;
    return this;
  }

  public Vertex unvisit() {
    this.visited = false;
    return this;
  }
}
