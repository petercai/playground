package cai.peter.algorithm.graph.kevinbacon;

import org.junit.Test;

import java.util.*;

public class Solution {

  private static void pm(Object o) {
    System.out.println(o);
  }

  public Map<String, Actor> actors;

  private class Actor {
    public final String name;
    public Set<Actor> costars;
    public int baconNumber;

    public Actor(String name) {
      this.name = name;
      costars = new HashSet<>();
      baconNumber = -1;
    }

    public void linkActor(Actor costar) {
      if (costar == null) return;
      costars.add(costar);
      costar.costars.add(this);
    }

    @Override
    public String toString() {
      String tmp = name + "(" + baconNumber + ")" + ": ";
      for (Actor an : costars.toArray(new Actor[0])) tmp += an.name + ", ";
      return tmp;
    }
  }

  public void bfsBuildBacon(String actor) {
    if (!actors.containsKey(actor)) throw new IllegalArgumentException("Invalid actor!");
    Queue<Actor> q = new LinkedList<>();
    Actor start = actors.get(actor);
    start.baconNumber = 0;
    q.offer(start);
    while (!q.isEmpty()) {
      Actor a = q.poll();
      for (Actor n : a.costars) {
        if (n.baconNumber == -1) {
          n.baconNumber = a.baconNumber + 1;
          q.offer(n);
        }
      }
    }
  }

  @Test
  public void testAC_BFS() {
    this.resetGraph();
    this.bfsBuildBacon("A");
    pm(actors.get("C").baconNumber);
  }

  @Test
  public void testAD_BFS() {
    this.resetGraph();
    this.bfsBuildBacon("A");
    pm(actors.get("D").baconNumber);
  }


    public void resetGraph() {
        actors = new HashMap<>();
        actors.put("A", new Actor("A"));
        actors.put("B", new Actor("B"));
        actors.put("C", new Actor("C"));
        actors.put("D", new Actor("D"));
        actors.put("E", new Actor("E"));
        actors.put("F", new Actor("F"));
        actors.put("G", new Actor("G"));
        actors.put("H", new Actor("H"));

        actors.get("A").linkActor(actors.get("B"));
        actors.get("B").linkActor(actors.get("C"));
        actors.get("C").linkActor(actors.get("D"));
        actors.get("D").linkActor(actors.get("E"));
        actors.get("A").linkActor(actors.get("F"));
        actors.get("F").linkActor(actors.get("E"));

        actors.get("A").linkActor(actors.get("C"));
        actors.get("A").linkActor(actors.get("E"));



        actors.get("G").linkActor(actors.get("H"));
    }

}
