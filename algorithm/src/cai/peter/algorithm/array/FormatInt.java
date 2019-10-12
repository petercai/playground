package cai.peter.algorithm.array;


public class FormatInt {
    public String formatInt(int v) {
        String s = String.valueOf(v);
        StringBuilder b = new StringBuilder();
        int j = s.length();
        for(int i=0;i<s.length();i++)
        {
            if(j--%3==0&&i>0) b.append(',');
            b.append(s.charAt(i));
        }
        return b.toString();
    }

    static public void main(String[] args)
    {
        int v=123456;
        System.out.println(v);
        System.out.println(new FormatInt().formatInt(v));
    }
}
