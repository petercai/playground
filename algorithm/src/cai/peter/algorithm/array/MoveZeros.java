package cai.peter.algorithm.array;

import org.junit.Test;

import java.util.Arrays;

import static cai.peter.algorithm.array.ArrayUtil.swap;

/*
Given an array nums, write a function to move all 0 ’s to the end of it while maintaining
the relative order of the non-zero elements.
For example, given nums = [ 0 , 1 , 0 , 3 , 12 ], after calling your function, nums should
be [ 1 , 3 , 12 , 0 , 0 ].
 */
public class MoveZeros {
    /*
Given an array nums, write a function to move all 0 ’s to the end of it while maintaining
the relative order of the non-zero elements.
For example, given nums = [ 0 , 1 , 0 , 3 , 12 ], after calling your function, nums should
be [ 1 , 3 , 12 , 0 , 0 ].
@bubble sorting
 */
    public void moveZero(int[] s) {
        int z = -1;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == 0) {
                if (z == -1 || s[z] != 0) z = i;
            } else if (z != -1) {
                swap(s, i, z);
                z++;
            }
        }
    }

    @Test
    public void testMoveZero() {
        int[] s = {0, 1, 0, 3, 12, 7, 8};
        System.out.println(Arrays.asList(s));
        moveZero(s);
        //    int[] ints = moveZero(s);
        System.out.println(Arrays.asList(s));
    }
}
