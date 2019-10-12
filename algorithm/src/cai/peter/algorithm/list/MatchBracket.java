package cai.peter.algorithm.list;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MatchBracket {
    final List<Character> leftBracket = Arrays.asList('(', '[', '{', '<');
    final List<Character> rightBracket = Arrays.asList(')', ']', '}', '>');

    public boolean isMatch(String s) {
        Stack<Character> stack = new Stack<>();
        int pos = -1;
        for (char c : s.toCharArray()) {
            if (leftBracket.contains((c))) {
                stack.push(c);
            } else if ((pos = rightBracket.indexOf(c)) >= 0) {
                int i = leftBracket.indexOf(stack.peek());
                if (i == pos) stack.pop();
            }
        }
        return stack.empty();
    }

    public boolean isMatch1(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                stack.pop();
            }
        }
        return stack.empty();
    }

    static public void main(String[] args) {
        MatchBracket m = new MatchBracket();
        String s = "(a[}bcd)";
        System.out.println(s + ": isMatch= " + m.isMatch(s));
    }
}
