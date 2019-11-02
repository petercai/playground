package cai.peter.algorithm.array;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindFirstMissingPosition {
    /*
    Given an unsorted integer array, ﬁnd the ﬁrst missing positive integer. For example,
    given [ 1 , 2 , 0 ] return 3 and [ 3 , 4 ,- 1 , 1 ] return 2

     start with 1
    s[i] = i+1 and s[i] = s[s[i]-1]
    */
    public int firstMissingPosition(int[] s) {
        for (int i = 0; i < s.length; i++) {
            while (s[i] != i + 1) {
                if (s[i] <= 0 || s[i] >= s.length) break;
                if (s[i] == s[s[i] - 1]) break;
                ArrayUtil.swap(s, i, s[i] - 1);
            }
        }
        for (int i = 0; i < s.length; i++) {
            if (s[i] != i + 1) return i + 1;
        }
        return 0;
    }

    @Test
    public void testFirstMissingPosition() {
        int[] s = {3, 4, -1, 1};
        assertEquals(2, firstMissingPosition(s));
        s = new int[] {2, 1, 0, 4};
        assertEquals(3, firstMissingPosition(s));
    }
}
