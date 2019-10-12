package cai.peter.algorithm.list;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    static public List toList(Node head) {
        ArrayList<Integer> res = new ArrayList<>();
        if( head != null ) {
            res.add(head.getValue());
            Node next = head.getNext();
            if (next != null)
                res.addAll(toList(next));
        }
        return res;
    }

    static public Node valueOf(List<Integer> l) {
        if(l==null) return  null;
        switch (l.size()) {
            case 0:
                return null;
            case 1:
                return new Node(l.get(0));
            default:
                return new Node(l.get(0), valueOf(l.subList(1, l.size())));
        }
    }
}
