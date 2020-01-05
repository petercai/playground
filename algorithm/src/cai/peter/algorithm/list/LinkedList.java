package cai.peter.algorithm.list;

import org.junit.Test;

import java.util.*;

public class LinkedList {

  public boolean hasCycle(Node head) {
    if (head == null) return false;
    Node slow = head, fast = head;
    while (fast != null && fast.getNext() != null) {
      slow = slow.getNext();
      fast = fast.getNext().getNext();
      if (slow.equals(fast)) return true;
    }
    return false;
  }

  public Node reverse(Node head) {
    if (head == null) return null;
    Node tmp = head;
    Node prev = head.getNext();
    tmp.setNext(null);

    while (prev != null) {
      head = prev;
      prev = head.getNext();
      head.setNext(tmp);
      tmp = head;
    }
    return head;
  }

  @Test
  public void main() {
    LinkedList e = new LinkedList();
    List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
    //    System.out.println(l);

    Node head = ListUtil.valueOf(l);
    System.out.println("head has cycle=" + hasCycle(head));

    ListUtil.createCycle(head, 2);
    boolean cycle = e.hasCycle(head);
    System.out.println("cycle has cycle=" + cycle);
    //        head = e.reverse(head);
    //        List list = ListUtil.toList(head);
    //        System.out.println(list);
  }
}
