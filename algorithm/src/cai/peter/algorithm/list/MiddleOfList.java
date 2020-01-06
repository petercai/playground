package cai.peter.algorithm.list;

import org.junit.Test;

import java.util.Arrays;

/*
Given a non-empty, singly linked list with head node head, return a middle node of linked list.

If there are two middle nodes, return the second middle node.



Example 1:

Input: [1,2,3,4,5]
Output: Node 3 from this list (Serialization: [3,4,5])
The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
Note that we returned a ListNode object ans, such that:
ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.
Example 2:

Input: [1,2,3,4,5,6]
Output: Node 4 from this list (Serialization: [4,5,6])
Since the list has two middle nodes with values 3 and 4, we return the second one.


https://leetcode.com/problems/middle-of-the-linked-list/solution/

When traversing the list with a pointer slow, make another pointer fast that traverses twice as fast.
When fast reaches the end of the list, slow must be in the middle.
 */
public class MiddleOfList {
  public Node middle(Node head) {
    if (head == null || head.getNext() == null) return head;

    Node fast = head, slow = head;
    while (fast != null && fast.getNext() != null) {
      slow = slow.getNext();
      fast = fast.getNext().getNext();
    }
    return slow;
  }

  @Test
  public void test() {
    Node head = ListUtil.valueOf(Arrays.asList(1));
    System.out.println(ListUtil.toList(middle(head)));
  }

  @Test
  public void test1() {
    Node head = ListUtil.valueOf(Arrays.asList(1, 2));
    System.out.println(ListUtil.toList(middle(head)));
  }

  @Test
  public void test2() {
    Node head = ListUtil.valueOf(Arrays.asList(1, 2, 3));
    System.out.println(ListUtil.toList(middle(head)));
  }

  @Test
  public void test3() {
    Node head = ListUtil.valueOf(Arrays.asList(1, 2, 3, 4, 5));
    System.out.println(ListUtil.toList(middle(head)));
  }
}
