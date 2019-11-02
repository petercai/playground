package cai.peter.algorithm.array;

public class ReverseInt {
    public int reverse(int v) {
        int rev = 0;
       while(v>0) {
            rev = rev * 10 + v%10;
            v/=10;
        }
        return rev;
    }

    public static void main(String[] args) {
        int v = 123456;
        System.out.println(v);
        System.out.println(new ReverseInt().reverse(v))
        ;
    }
}
