package cai.peter.algorithm.array;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Queue;

/*
Given an array of meeting time intervals consisting of start and end times [[s 1 ,e 1 ],[s 2 ,e 2 ],...]
ﬁnd the minimum number of conference rooms required.
 */
public class MeetingRooms {
  @AllArgsConstructor
  class Interval {
    int start, end;
  }

  public int calMeetingRooms(Interval[] s) {
    Queue<Integer> q = new PriorityQueue<>();
    for (Interval i : s) {
      if (q.peek() == null) q.offer(i.end); // always add first
      else {
        q.offer(i.end); // add one by one
        if (i.start >= q.peek()) q.poll();
      }
    }
    return q.size();
  }

  @Test
  public void testMettingRoo() {
    Interval[] is = {
      new Interval(1, 2), new Interval(2, 3),new Interval(4, 5), new Interval(4, 5), new Interval(3, 6)
    };
    Assert.assertEquals(3, calMeetingRooms(is));
  }
}
