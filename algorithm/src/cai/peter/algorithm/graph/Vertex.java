package cai.peter.algorithm.graph;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Data
@RequiredArgsConstructor
public class Vertex {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Vertex vertex = (Vertex) o;
    return value == vertex.value &&
            name.equals(vertex.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, name);
  }

  private final int value;
  private final String name;
  private boolean visited;

  @Override
  public String toString() {
    return name+"("+value+")";
  }

  public Vertex visit() {
    this.visited = true;
    return this;
  }

  public Vertex unvisit() {
    this.visited = false;
    return this;
  }
}
