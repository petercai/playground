package cai.peter.algorithm.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/*
Given two strings s and t, determine if they are isomorphic.
Two strings are isomorphic if the characters in s can be replaced to get t.

For example,"egg" and "add" are isomorphic, "foo" and "bar" are not.
 */
public class IsomorphicStrings {
  public boolean check(String s1, String s2) {
    Map<Character, Character> m1 = new HashMap<>(), m2 = new HashMap<>();
    for (int i = 0; i < Math.max(s1.length(), s2.length()); i++) {
      Character c1 = i >= s1.length() ? null : s1.charAt(i),
          c2 = i > s2.length() ? null : s2.charAt(i);

      if (m1.containsKey(c1)) {
        char v2 = m1.get(c1);
        if (c2 != v2) return false;
      } else if (c1 != null) m1.put(c1, c2);

      if (m2.containsKey(c2)) {
        char v1 = m2.get(c2);
        if (v1 != c1) return false;
      } else if (c2 != null) m2.put(c2, c1);
    }
    return true;
  }

  @Test
  public void test() {
      Assert.assertTrue(check("egg","add"));
      Assert.assertFalse(check("foo","bare"));

  }
}
