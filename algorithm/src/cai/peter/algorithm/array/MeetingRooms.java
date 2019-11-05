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
    Queue<Integer> q = new PriorityQueue<Integer>();
    q.offer(s[0].end);
    int count = 0;
    for (Interval i : s) {
      if (i.start <= q.peek()) { // compare with one wil finish earlist
        count++; // there is overlap
      } else q.poll(); // remove earlist
      q.offer(i.end); // add this one
    }
    return count;
  }

  @Test
  public void testMettingRoo()
  {
    Interval[] is = {new Interval(1, 2),new Interval(2,3), new Interval(4,5),new Interval(3,6)};
    Assert.assertEquals(2,calMeetingRooms(is));
  }
}
