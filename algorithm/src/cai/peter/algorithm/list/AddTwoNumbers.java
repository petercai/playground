package cai.peter.algorithm.list;

import org.junit.Test;

import java.util.Arrays;

/*
You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Java Solution


 */
public class AddTwoNumbers {
  public Node addTwo(Node first, Node second) {
    Node head = null, cur = null;
    int carry = 0;
    while (first != null || second != null || carry > 0) {
      int sum =
          carry + (first == null ? 0 : first.getValue()) + (second == null ? 0 : second.getValue());
      Node tmp = new Node(sum % 10);
      if (head == null) {
        head = tmp;
        cur = tmp;
      } else {
        carry = sum / 10;
        cur.setNext(tmp);
        cur = tmp;
      }
      first = first == null ? null : first.getNext();
      second = second == null ? null : second.getNext();
    }
    return head;
  }

  @Test
  public void test() {
    Node s = ListUtil.valueOf(Arrays.asList(2, 4, 3));
    Node f = ListUtil.valueOf(Arrays.asList(5, 6, 6, 9));
    Node res = addTwo(f, s);
    System.out.println(ListUtil.toList(res));
  }
}
