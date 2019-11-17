package cai.peter.algorithm.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class  NodeAbstract<T> {
    private final T val;
    private NodeAbstract left,right;
}
