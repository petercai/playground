package cai.peter.algorithm.array;

import org.junit.Test;

/*
In computer science, the longest common substring problem is to find the longest
string that is a substring of two or more strings.
 */
public class CommonSubstring {
  public int getLenght(String a, String b) {
    int alen = a.length();
    int blen = b.length();
    int max = 0;
    int[][] tbl = new int[alen][blen];
    for (int i = 0; i < alen; i++) {
      for (int j = 0; j < blen; j++) {
        if (a.charAt(i) == b.charAt(j)) {
          if (i == 0 || j == 0) tbl[i][j] = 1;
          else tbl[i][j] = tbl[i - 1][j - 1] + 1;
        }
        max = Math.max(max, tbl[i][j]);
      }
    }
    return max;
  }

  @Test
    public void test(){
      System.out.println(getLenght("de","abcdefgh"));
  }
}
