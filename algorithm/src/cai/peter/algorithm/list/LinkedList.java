package cai.peter.algorithm.list;

import java.util.*;

public class LinkedList {
    Node head;

    public Node reverse(Node head) {
        if (head == null) return null;
        Node tmp = head;
        Node prev = head.getNext();
        tmp.setNext(null);

        while(prev!=null){
            head = prev;
            prev = head.getNext();
            head.setNext(tmp);
            tmp = head;
        }
        return head;
    }

    public List toList(Node head) {
        ArrayList<Integer> res = new ArrayList<>();
        if( head != null ) {
            res.add(head.getValue());
            Node next = head.getNext();
            if (next != null)
                res.addAll(toList(next));
        }
        return res;
    }


    public Node valueOf(List<Integer> l) {
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


    static public void main(String[] args) {
        LinkedList e = new LinkedList();
        List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        System.out.println(l);
        Node head = e.valueOf(l);
        head = e.reverse(head);
        List list = e.toList(head);
        System.out.println(list);
    }
}
