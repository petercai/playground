package cai.peter.algorithm.array;

import org.junit.Test;

public class FormatInt {
  public String formatInt(int v) {
    String s = String.valueOf(v);
    StringBuilder b = new StringBuilder();
    int len = s.length();
    for (int i = 0; i < len; i++) {
      if (i > 0 && (len - i) % 3 == 0) b.append(',');
      b.append(s.charAt(i));
    }
    return b.toString();
  }

  @Test
  public void test() {

    System.out.println(formatInt(23));
    System.out.println(formatInt(123));
    System.out.println(formatInt(1234));
    System.out.println(formatInt(123456));
  }
}
