package cai.peter.algorithm.list;

import org.junit.Test;

import java.util.Arrays;

public class ListInsertSort {
  public Node sort(Node head) {
    if (head == null || head.getNext() == null) return head;

    Node result = new Node(head.getValue());
    head = head.getNext();
    /*
    iterate the list
     */
    while (head != null) {
      /*
      insert into new list
       */
      Node cur = result, before = result;
      while (cur != null) {

        if (head.getValue() > cur.getValue()) {
          // insert at end
          if (cur.getNext() == null) {
            cur.setNext(new Node(head.getValue()));
            break;
          }
        } else {
          // insert at head
          if (cur.equals(result)) {
            result = new Node(head.getValue(), cur);
            break;//done
          } else {
            // insert middle
            before.setNext(new Node(head.getValue(), cur));
            break;//done
          }
        }
        before = cur;
        cur = cur.getNext();
      }

      head = head.getNext();
    }
    return result;
  }

  @Test
  public void test() {
    Node head = ListUtil.valueOf(Arrays.asList(3, 2, 6, 4, 1, 7));
    System.out.println(ListUtil.toList(sort(head)));
  }
}
