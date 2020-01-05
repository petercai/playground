package cai.peter.algorithm.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.
Valid operators are +, -, *, /. Each operand may be an integer or another expression.

For example:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
public class ReversePolishNotation {
  public int evaluate(String[] a) {
    final String operator = "+-*/";
    Stack<Integer> s = new Stack<>();
    for (String v : a) {
      if (!operator.contains(v)) s.push(Integer.valueOf(v));
      else {
        switch (v) {
          case "+":
            s.push(s.pop() + (int) s.pop());
            break;
          case "-":
            int second = s.pop(), first = s.pop();
            s.push(first - second);
            break;
          case "*":
            s.push(s.pop() * s.pop());
            break;
          case "/":
            int right = s.pop(), left = s.pop();
            s.push(left / right);
            break;
        }
      }
    }

    return s.pop();
  }

  @Test
  public void test() {
    String[] s1 = new String[] {"2", "1", "+", "3", "*"};
    Assert.assertEquals(9, evaluate(s1));
    s1 = new String[] {"4", "13", "5", "/", "+"};
    Assert.assertEquals(6, evaluate(s1));
  }
}
