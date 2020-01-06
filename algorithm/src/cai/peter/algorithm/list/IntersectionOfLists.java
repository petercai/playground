package cai.peter.algorithm.list;

/*

https://leetcode.com/problems/intersection-of-two-linked-lists/

Two Pointers approach:

Maintain two pointers pApA and pBpB initialized at the head of A and B, respectively.
Then let them both traverse through the lists, one node at a time.

When pApA reaches the end of a list, then redirect it to the head of B (yes, B, that's right.);
similarly when pBpB reaches the end of a list, redirect it the head of A.

If at any point pApA meets pBpB, then pApA/pBpB is the intersection node.

To see why the above trick would work, consider the following two lists:
A = {1,3,5,7,9,11} and
B = {2,4,9,11}, which are intersected at node '9'.

Since B.length (=4) < A.length (=6), pBpB would reach the end of the merged list first, because pBpB traverses exactly 2 nodes less than pApA does.

By redirecting pBpB to head A, and pApA to head B, we now ask pBpB to travel exactly 2 more nodes than pApA would.
So in the second iteration, they are guaranteed to reach the intersection node at the same time.

If two lists have intersection, then their last nodes must be the same one.
So when pApA/pBpB reaches the end of a list, record the last element of A/B respectively.
If the two last elements are not the same one, then the two lists have no intersections.

https://www.geeksforgeeks.org/write-a-function-to-get-the-intersection-point-of-two-linked-lists/

Method 3(Using difference of node counts)

Get count of the nodes in the first list, let count be c1.
Get count of the nodes in the second list, let count be c2.
Get the difference of counts d = abs(c1 – c2)
Now traverse the bigger list from the first node till d nodes so that from here onwards both the lists have equal no of nodes.
Then we can traverse both the lists in parallel till we come across a common node. (Note that getting a common node is done by comparing the address of the nodes)

Method 6 (Traverse both lists and compare addresses of last nodes)
This method is only to detect if there is an intersection point or not. (Thanks to NeoTheSaviour for suggesting this)
 */
public class IntersectionOfLists {
}
