package cai.peter.algorithm.array.sort.O_n2;

import cai.peter.algorithm.array.ArrayUtil;
import org.junit.Test;

import java.util.Arrays;

/*
Insertion Sort is an efficient algorithm for ordering a small number of items.
This method is based on the way card players sort a hand of playing cards.

We start with an empty left hand and the cards laid down on the table.
We then remove one card at a time from the table and insert it into the correct position in the left hand.
To find the correct position for a new card,
we compare it with the already sorted set of cards in the hand, from right to left.

Peter: it's fast for LinkedList implementation. for array, there are data block movement for insertion

 */
public class InsertSort {
    public void sort(int[] a){
        if( a==null || a.length < 2) return;
        for(int i=1;i<a.length;i++){
            int j=i-1;
            int v = a[i];
            /*
            move data block for insertion
             */
            while(j>=0 && v<a[j])
            {
                /*
                move data one by one backward until find the right position
                 */
                a[j+1]= a[j];
                j--;
            }
            /*
            insert
             */
            a[j+1] = v;
        }
    }

    @Test
    public void test(){
        int[] a= ArrayUtil.a;
    System.out.println(Arrays.toString(a));
    sort(a);
    System.out.println(Arrays.toString(a));
    }
}
