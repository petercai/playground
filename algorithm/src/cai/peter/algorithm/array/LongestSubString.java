package cai.peter.algorithm.array;

import org.junit.Test;

/*
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubString {
  public int length(String s) {
    int len = s.length(), ans = 0;
    int[] seq = new int[128];
    /*
     * try to extend the range of [i,j]
     */
    for (int left = 0, right = 0; right < len; right++) { // right pointer keep moving
      char pos = s.charAt(right);
      left = Math.max(seq[pos], left);
      ans = Math.max(ans, right - left + 1);
      seq[pos] = right + 1; // set to next position
      left = left;
    }
    return ans;
  }

  @Test
  public void test() {
    //        System.out.println(length("abcabcbb"));
    System.out.println(length("pwwkew"));
  }
}
