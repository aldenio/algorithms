package medium;

public class Solution {

  /**
   * LeetCode 189. Rotate Array
   * 
   * Given an array, rotate the array to the right by k steps, where k is
   * non-negative.
   */
  public void rotate(int[] nums, int k) {
    // the rotation can be bigger than the array size
    int shift = k % nums.length;

    // if there is nothing to rotate, it is all done.
    if (shift == 0 || nums.length < 2)
      return;

    // create a buffer of the shift size.
    int[] copy = new int[shift];
    // copy the last shift numbers to the buffer
    System.arraycopy(nums, nums.length - shift, copy, 0, shift);
    // shift the numbers to the right
    System.arraycopy(nums, 0, nums, shift, nums.length - shift);
    // copy buffered numbers to the beginning of the array
    System.arraycopy(copy, 0, nums, 0, shift);
  }

  /**
   * LeetCode 167. Two Sum II - Input Array Is Sorted
   * 
   * Given a 1-indexed array of integers numbers that is already sorted in
   * non-decreasing order, find two numbers such that they add up to a specific
   * target number.
   * Let these two numbers be numbers[index1] and numbers[index2] where 1 <=
   * index1 < index2 <= numbers.length.
   * Return the indices of the two numbers, index1 and index2, added by one as an
   * integer array [index1, index2] of length 2.
   * 
   * Notes:
   * The tests are generated such that there is exactly one solution.
   * You may not use the same element twice.
   * Your solution must use only constant extra space.
   */
  public int[] twoSum(int[] numbers, int target) {
    int l = 0;
    int r = numbers.length - 1;
    while (l < r) {
      int sum = numbers[l] + numbers[r];
      if (sum > target) {
        r--;
      } else if (sum < target) {
        l++;
      } else {
        break;
      }
    }
    return new int[] { l + 1, r + 1 };
  }

  /**
   * LeetCode: 19. Remove Nth Node From End of List
   * 
   * Given the head of a linked list, remove the nth node from the end of the list
   * and return its head.
   * 
   * Definition for singly-linked list.
   * public class ListNode {
   * int val;
   * ListNode next;
   * ListNode() {}
   * ListNode(int val) { this.val = val; }
   * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   * }
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode node = head;
    ListNode toRemove = head;
    // walk node pointer n nodes ahead
    while (node.next != null && n >= 1) {
      node = node.next;
      n--;
    }
    // if node pointer points to the end of the list, change list header.
    if (node.next == null && n == 1) {
      return head.next;
    }
    // if there is still list to run, move both pointer at the same time.
    while (node.next != null) {
      node = node.next;
      toRemove = toRemove.next;
    }
    // toRemove will point to the node exactly before the node to be removed, só
    // remove it.
    toRemove.next = toRemove.next.next;
    return head;
  }

  /**
   * LeetCode 3. Longest Substring Without Repeating Characters
   * Given a string s, find the length of the longest substring without repeating
   * characters.
   */
  public int lengthOfLongestSubstring(String s) {
    throw new UnsupportedOperationException();
  }

  /**
   * This class is here just to let questions compile.
   */
  class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
}