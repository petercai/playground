package cai.peter.algorithm.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraveral {
  public List<List<Integer>> levelOrderBFS(Node root) {
    List result = new ArrayList();

    if (root == null) {
      return result;
    }

    Queue<Node> queue = new LinkedList<Node>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      ArrayList<Integer> level = new ArrayList<Integer>();
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        Node head = queue.poll();
        level.add(head.getValue());
        if (head.getLeft() != null) {
          queue.offer(head.getLeft());
        }
        if (head.getRight() != null) {
          queue.offer(head.getRight());
        }
      }
      result.add(level);
    }

    return result;
  }

  @Test
  public void test() {
    Node root = BTree.insertLevelOrder(new int[] {1, 2, 3}, 0);
    List<List<Integer>> result = levelOrderBFS(root);
    System.out.println(result);
  }

  public List<List<Integer>> levelOrder(Node root) {
    List<List<Integer>> results = new ArrayList<List<Integer>>();

    if (root == null) {
      return results;
    }

    int maxLevel = 0;
    while (true) {
      List<Integer> level = new ArrayList<Integer>();
      dfs(root, level, 0, maxLevel);
      if (level.size() == 0) {
        break;
      }

      results.add(level);
      maxLevel++;
    }

    return results;
  }

  private void dfs(Node root, List<Integer> level, int curtLevel, int maxLevel) {
    if (root == null || curtLevel > maxLevel) {
      return;
    }

    if (curtLevel == maxLevel) {
      level.add(root.getValue());
      return;
    }

    dfs(root.getLeft(), level, curtLevel + 1, maxLevel);
    dfs(root.getRight(), level, curtLevel + 1, maxLevel);
  }
}
