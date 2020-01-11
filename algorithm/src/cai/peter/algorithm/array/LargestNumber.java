package cai.peter.algorithm.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/*
Given a list of non negative integers,
arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
(Note: The result may be very large, so you need to return a string instead of an integer.)


This problem can be solved by sorting strings, not sorting integer.

 */
public class LargestNumber {
    public String largest(int[] a){
        String[] str = new String[a.length];
        for(int i=0;i<a.length;i++){
            str[i] = String.valueOf(a[i]);
        }
        Arrays.sort(str, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2+o1).compareTo(o1+o2);
            }
        });
        StringBuilder b = new StringBuilder();
        for(String s: str) b.append(s);
        while(b.charAt(0)=='0'&&b.length()>1)
            b.deleteCharAt(0);

        return b.toString();
    }

    @Test
    public void test(){
        int[] a = new int[]{3, 30, 34, 5, 9};
        System.out.println(largest(a));
    }
}
