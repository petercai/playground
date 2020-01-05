package cai.peter.algorithm.array;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/*
Given a collection of intervals, merge all overlapping intervals.

Example 1:
Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:
Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

 */
public class MergeIntervals {

  @AllArgsConstructor
  @Data
  static class Interval {
    private Integer start, end;

      @Override
      public String toString() {
          return "[" +
                  start +
                  "," + end +
                  ']';
      }
  }

  public List<Interval> merge(List<Interval> intervals) {
    Collections.sort(intervals, Comparator.comparing(i -> i.getStart()));
    List<Interval> result = new ArrayList<>();
    Interval p = intervals.get(0);
    for (int i = 1; i < intervals.size(); i++) {
      Interval c = intervals.get(i);
      if (c.getStart() <= p.getEnd()) p.setEnd(Math.max(c.getEnd(), p.getEnd())); // merge
      else {
        result.add(p);
        p = c;
      }
    }
    result.add(p);
    return result;
  }

  @Test
  public void test() {
    System.out.println(
        merge(
            Arrays.asList(
                new Interval(1, 3),
                new Interval(8, 10),
                new Interval(2, 6),
                new Interval(15, 18))));
  }
}
