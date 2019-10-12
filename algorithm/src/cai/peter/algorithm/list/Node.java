package cai.peter.algorithm.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Node {
    private final int value;
    private Node next;
}
