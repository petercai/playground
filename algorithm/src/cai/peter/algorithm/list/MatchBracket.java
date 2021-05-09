package cai.peter.algorithm.list;

import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import org.junit.Test;

public class MatchBracket {
  final List<Character> leftBracket = Arrays.asList('(', '[', '{', '<');
  final List<Character> rightBracket = Arrays.asList(')', ']', '}', '>');

  public boolean isMatch(String s) {
    Deque<Character> stack = new ArrayDeque<>();
    int pos = -1;
    for (char c : s.toCharArray()) {
      if (leftBracket.contains((c))) {
        stack.push(c);
      } else if ((pos = rightBracket.indexOf(c)) >= 0) {
        int i = leftBracket.indexOf(stack.peek());
        if (i == pos) stack.pop();
      }
    }
    return stack.size()==0;
  }

  public boolean isMatch1(String s) {
    Deque<Character> stack = new ArrayDeque<>();
    for (char c : s.toCharArray()) {
      if (c == '(') {
        stack.push(c);
      } else if (c == ')') {
        if (stack.size()==0) return false;
        else stack.pop();
      }
    }
    return stack.size()==0;
  }

  @Test
  public  void test() {
    MatchBracket m = new MatchBracket();
    String s = "(a[}bcd)";
    assertTrue(m.isMatch1(s));
    assertTrue(m.isMatch1("()"));
    assertTrue(!m.isMatch1(")("));
  }


}
