package cai.peter.algorithm.array;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    int len = s.length(), res = 0;
    int[] seq = new int[128];
    /*
     * try to extend the range of [i,j]
     */
    for (int left = 0, right = 0; right < len; right++) { // right pointer keep moving
      char pos = s.charAt(right);
      left = Math.max(seq[pos], left); // catch up right poiter
      res = Math.max(res, right - left + 1);
      seq[pos] = right + 1; // set to right's next position for left pointer
    }
    return res;
  }

  public int lengthMap(String s) {
    int res = 0;
    Map<Character, Integer> cmap = new HashMap<>();
    for (int left = 0, right = 0; right < s.length(); right++) {
      char pos = s.charAt(right);
      if (cmap.containsKey(pos)) left = Math.max(left, cmap.get(pos));
      res = Math.max(res, right - left + 1);
      cmap.put(pos, right + 1);
    }
    return res;
  }

  /*
    A sliding window is an abstract concept commonly used in array/string problems.
    A window is a range of elements in the array/string which usually defined by the start and end indices, i.e. [i, j)[i,j) (left-closed, right-open).
    A sliding window is a window "slides" its two boundaries to the certain direction.
    For example, if we slide [i, j)[i,j) to the right by 11 element, then it becomes [i+1, j+1)[i+1,j+1) (left-closed, right-open).

    Back to our problem.
    We use HashSet to store the characters in current window [i, j)[i,j) (j = ij=i initially).
    Then we slide the index jj to the right.
    If it is not in the HashSet, we slide jj further.
    Doing so until s[j] is already in the HashSet.
    At this point, we found the maximum size of substrings without duplicate characters start with index ii.
    If we do this for all ii, we get our answer.
     */
  public int lengthSet(String s) {
    int res = 0;
    Set<Character> cSet = new HashSet<>();
    int left = 0, right = 0;
    while (left < s.length() && right < s.length()) {
      if (!cSet.contains(s.charAt(right))) {
        cSet.add(s.charAt(right++));
        res = Math.max(res, right - left);
      } else {
        cSet.remove(s.charAt(left++));
      }
    }
    return res;
  }

  @Test
  public void test() {
    //        System.out.println(length("abcabcbb"));
    //    System.out.println(length("pwwkew"));
    //    System.out.println(lengthSet("pwwwwwwkew"));
    System.out.println(lengthMap("pwwwwwwkew"));
  }
}
