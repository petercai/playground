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
    int count = 1;
    for (Interval i : s) {
      if (i.start < q.peek()) {
        count++;
      } else q.poll();
      q.offer(i.end);
    }
    return count;
  }

  @Test
  public void testMettingRoo()
  {
    Interval[] is = {new Interval(1, 2),new Interval(3,4), new Interval(3,5),new Interval(5,6)};
    Assert.assertEquals(2,calMeetingRooms(is));
  }
}
