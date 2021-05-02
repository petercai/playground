package cai.peter.algorithm.list;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class MyLinkedList {
  private class Node {
    Node next;
    int val;

    public Node(int val) {
      this.val = val;
    }
  }



  boolean detectLoop(Node head)
  {
    Node slow_p = head, fast_p = head;
    while (fast_p != null
      && fast_p.next != null) {
        slow_p = slow_p.next;
        fast_p = fast_p.next.next;
        if (slow_p == fast_p) {
          return true;
      }
    }
    return false;
  }

  public static Node reverseOrder(Node head) {

    if (head == null || head.next == null) {
      return head;
    }

    Node pre = head;
    Node curr = head.next;

    while (curr != null) {
      Node temp = curr.next;
      curr.next = pre;
      pre = curr;
      curr = temp;
    }

    // set head node's next
    head.next = null;

    return pre;
  }
}
