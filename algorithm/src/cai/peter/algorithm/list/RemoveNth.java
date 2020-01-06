package cai.peter.algorithm.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/*
Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
 */
public class RemoveNth {
    /*
    (Fast-Slow Pointers)
     */
    public Node removeEnd(Node head, int n){
        Node fast = head;
        /*
        move fast to right postion
         */
        for(int i=0;i<n-1;i++){
            fast = fast.getNext();
            if( fast ==null ) return head;
        }
        /*
        remove head if fast already reach end
         */
        if( fast.getNext() == null) {
            return head.getNext();
        }
        /*
        move both fast and slow until slow reach end
         */
        Node slow = head, prev = head;
        while(fast.getNext()!=null){
            prev = slow;
            slow = slow.getNext();
            fast = fast.getNext();
        }
        /*
        remove nth from end
         */
        prev.setNext(slow.getNext());
        return head;
    }

    @Test
    public void test(){
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        System.out.print(data+"==>");
        System.out.println(ListUtil.toList(removeEnd(ListUtil.valueOf(data), 2)));
    }

    @Test
    public void test1(){
        List<Integer> data = Arrays.asList(1, 2);
        System.out.print(data+"==>");
        Node head = ListUtil.valueOf(data);
        System.out.println(ListUtil.toList(removeEnd(head,2)));
    }
}
