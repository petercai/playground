package cai.peter.algorithm.list;

import org.junit.Test;

import java.util.Arrays;

/*
Given a sorted linked list, delete all duplicates such that each element appear only
once.
For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
 */
public class RemoveDuplicates {
    public void removeDuplicate(Node head){
        if( head == null ) return;
        Node pre = head, cur = head.getNext();
        while(cur!=null){
            if(pre.getValue()==cur.getValue()){
                cur = cur.getNext();
                pre.setNext(cur);
            }
            else{
                cur = cur.getNext();
                pre = pre.getNext();
            }
        }
    }

    @Test
    public void test(){
        Node head = ListUtil.valueOf(Arrays.asList(1, 2, 2, 3, 4, 4, 5, 6));
        removeDuplicate(head);
        System.out.println(ListUtil.toList(head));
    }
}
