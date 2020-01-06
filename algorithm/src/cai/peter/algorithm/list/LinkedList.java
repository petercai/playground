package cai.peter.algorithm.list;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class LinkedList {

  public boolean hasCycle(Node head) {
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
  public void testCycle() {
    List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

    Node head = ListUtil.valueOf(l);
    Assert.assertEquals(false, hasCycle(head));

    ListUtil.createCycle(head, 2);
    Assert.assertEquals(true,hasCycle(head));

  }

  @Test
  public void testReverse(){
    Node head = ListUtil.valueOf(Arrays.asList(1,2,3,4,5,6,7,8,9));
    System.out.println(ListUtil.toList(reverse(head)));
  }
}
