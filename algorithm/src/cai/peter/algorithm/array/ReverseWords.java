package cai.peter.algorithm.array;

import java.util.Arrays;

public class ReverseWords {

    public String reverseWords(String s){
        char[] cs = s.toCharArray();
        int i=0;
        for(int j=0;j<cs.length;j++){
            if(cs[j]==' '){
                reverse(cs,i,j-1);
                i=j+1;
            }
        }
        reverse(cs,i, cs.length-1);
        reverse(cs, 0, cs.length-1);
        return new String(cs);
    }
    void reverse(char[] s , int i, int j){
        while(i<j){
            char t = s[i];
            s[i] = s[j];
            s[j] = t;
            i++;
            j--;
        }
    }
    static public void main(String[] args){
        String s= "the sky is bule";
        System.out.println(s);
        System.out.println(new ReverseWords().reverseWords(s));
    }
}
