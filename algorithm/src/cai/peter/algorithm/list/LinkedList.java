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


    static public void main(String[] args) {
        LinkedList e = new LinkedList();
        List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        System.out.println(l);
        Node head = ListUtil.valueOf(l);
        head = e.reverse(head);
        List list = ListUtil.toList(head);
        System.out.println(list);
    }
}
