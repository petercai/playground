package cai.peter.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
Given two words (start and end), and a dictionary, ﬁnd the length of shortest transfor-
mation sequence from start to end, such that only one letter can be changed at a time
and each intermediate word must exist in the dictionary. For example, given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
 */
public class WordLadder {
  public String[] ladder(String start, String end, String[] dic) {
    Set<String> set = Arrays.stream(dic).collect(Collectors.toSet());
    set.add(end);
    List<String> res = new ArrayList<>();
    res.add(start);
    while (!end.equals(start) && set.size() > 0) {
      boolean mf = true;
      for (int i = 0; i < start.length() && mf; i++) {
        char[] c = start.toCharArray();
        for (char j = 'a'; j <= 'z'; j++) {
          c[i] = j;
          String tmp = new String(c);
          if (set.contains(tmp)) {
            mf = false;
            start = tmp;
            res.add(start);
            set.remove(start);
            break;
          }
        }
      }
    }
    return res.toArray(new String[0]);
  }

  public static void main(String[] args) {
    String[] ladder =
        new WordLadder().ladder("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log"});
    System.out.println(Arrays.asList(ladder));
  }
}
