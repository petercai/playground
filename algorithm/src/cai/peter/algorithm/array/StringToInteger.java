package cai.peter.algorithm.array;

import org.junit.Assert;
import org.junit.Test;

public class StringToInteger {
  public int atoi(String s) {
      /* skip validation */
    int result = 0;
    int sign = 1;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (i == 0 && c == '-') sign = -1;
      else if (c >= '0' && c <= '9') {
        result = result * 10 + (c - '0');
      } else throw new IllegalArgumentException("Not a number");
    }

    return result*sign;
  }

  @Test
  public void test() {
    Assert.assertEquals(123, atoi("123"));
    Assert.assertEquals(-123, atoi("-123"));
    Assert.assertEquals(0, atoi("0"));
  }
}
