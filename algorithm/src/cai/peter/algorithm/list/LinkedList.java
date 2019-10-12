package cai.peter.algorithm.list;

import java.util.*;

public class LinkedList {

    public boolean hasCycle(Node head){
        Node slow = head;
        Node fast = head;
        while(fast!=null&&fast.getNext()!=null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if(slow == fast)
                return true;
        }
        return false;
    }

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
        ListUtil.createCycle(head,2);

        boolean cycle = e.hasCycle(head);
        System.out.println("Has cycle="+cycle);
//        head = e.reverse(head);
//        List list = ListUtil.toList(head);
//        System.out.println(list);
    }
}
