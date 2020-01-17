package cai.peter.algorithm.graph;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class Edge implements Comparable<Edge>{
    private final Vertex start, end;
    private final double weight;

    @Override
    public int compareTo(Edge o) {
        return BigDecimal.valueOf(this.weight).compareTo(BigDecimal.valueOf(o.weight));
    }

    @Override
    public String toString() {
        return String.format("(%s -> %s, %f)",start.getName(),end.getName(),weight);
    }
}
