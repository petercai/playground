package cai.peter.algorithm.graph.kevinbacon;
import org.junit.Test;

import java.util.*;
public class Solution {

    private static void pm(Object o) { System.out.println(o); }
    public Map<String,ActorNode> actors;

    private class ActorNode {
        public final String name;
        public Set<ActorNode> costars;
        public int baconNumber;
        public ActorNode(String name) {
            this.name = name;
            costars = new HashSet<>();
            baconNumber = -1;
        }
        public void linkActor(ActorNode costar) {
            if (costar == null) return;
            costars.add(costar);
            costar.costars.add(this);
        }
        @Override public String toString() {
            String tmp = name + "(" + baconNumber + ")" + ": ";
            for (ActorNode an : costars.toArray(new ActorNode[0]))
                tmp += an.name + ", ";
            return tmp;
        }
    }

    public void resetGraph() {
        actors = new HashMap<>();
        actors.put("A", new ActorNode("A"));
        actors.put("B", new ActorNode("B"));
        actors.put("C", new ActorNode("C"));
        actors.put("D", new ActorNode("D"));
        actors.put("E", new ActorNode("E"));
        actors.put("F", new ActorNode("F"));
        actors.put("G", new ActorNode("G"));
        actors.put("H", new ActorNode("H"));

        actors.get("A").linkActor(actors.get("B"));
        actors.get("B").linkActor(actors.get("C"));
        actors.get("C").linkActor(actors.get("D"));
        actors.get("D").linkActor(actors.get("E"));
        actors.get("A").linkActor(actors.get("F"));
        actors.get("F").linkActor(actors.get("E"));

        actors.get("G").linkActor(actors.get("H"));
    }

    public Solution() {
        resetGraph();
    }

    public void runBacon(String startActor) {
        if (!actors.containsKey(startActor)) {
            pm("actor does not exist");
            return;
        }
        Queue<ActorNode> q = new ArrayDeque<>();
        ActorNode start = actors.get(startActor);
        start.baconNumber = 0;
        q.add(start);
        while (!q.isEmpty()) {
            ActorNode cur = q.remove();
            for (ActorNode an : cur.costars.toArray(new ActorNode[0])) {
                if (an.baconNumber == -1) {
                    an.baconNumber = cur.baconNumber + 1;
                    q.add(an);
                }
            }
        }
    }

    @Test
    public void testAC(){
        this.resetGraph();
        runBacon("A");
        pm(actors.get("C").baconNumber);
    }

    public static void main(String[] args) {
        Solution m = new Solution();
        m.runBacon("A");
        pm(m.actors.get("C").baconNumber);
    }
}
