package cai.peter.algorithm.graph;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.Objects;

@Data
@RequiredArgsConstructor
public class Vertex {

  private final int value;
  private final String name;
  private boolean visited;

  private LinkedList<Edge> edges=new LinkedList<>();

  public Vertex visit() {
    this.visited = true;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Vertex vertex = (Vertex) o;
    return value == vertex.value && name.equals(vertex.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, name);
  }

  @Override
  public String toString() {
    return name + "(" + value + ")";
  }
}
