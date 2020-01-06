package cai.peter.algorithm.list;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtil {
  public static List<Integer> toList(Node head) {
      List<Integer> res = new ArrayList<>();
      while(head !=null){
          res.add(head.getValue());
          head = head.getNext();
      }
      return res;
  }

  @Test
  public void testToList(){
        Node head = valueOf(Arrays.asList(1,2,3,4,5,6,7,8,9));
        System.out.println(toList(head));
  }

  public static void createCycle(Node head, Integer cycle) {
    Node c = null;
    while (head.getNext() != null) {
      if (head.getValue() == cycle) c = head;
      head = head.getNext();
    }
    head.setNext(c);
  }

  public static Node valueOf(List<Integer> l) {
    Node head = null, cur = null;
    if (l == null || l.isEmpty()) return null;
    for (int i = 0; i < l.size(); i++) {
      int v = l.get(i);
      if (i == 0) {
        head = new Node(v);
        cur = head;
      } else {
        cur.setNext(new Node(v));
        cur = cur.getNext();
      }
    }
    return head;
  }

  @Test
  public void testValueOf() {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    System.out.println(list);
    Node head = valueOf(list);
    for (int i : list) {
      Assert.assertEquals(i, head.getValue());
      head = head.getNext();
    }
  }
}
