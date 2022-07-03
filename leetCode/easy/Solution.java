package leetCode.easy;

import java.util.ArrayList;
import java.util.List;

public class Solution {

  /**
   * LeetCode 4. Median of Two Sorted Arrays
   * 
   * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
   * The overall run time complexity should be O(log (m+n)).
   */
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int[] merged = merge(nums1, nums2);
    int middle = (merged.length / 2);
    if (merged.length % 2 == 1) {
      return merged[middle];
    } else {
      return (merged[middle - 1] + merged[middle]) / 2.0;
    }
  }

  public int[] merge(int[] a, int[] b) {
    int[] result = new int[a.length + b.length];
    int ia = 0;
    int ib = 0;
    for (int i = 0; i < result.length; i++) {
      if (ib >= b.length) {
        result[i] = a[ia];
        ia++;
      } else if (ia >= a.length) {
        result[i] = b[ib];
        ib++;
      } else if (a[ia] < b[ib]) {
        result[i] = a[ia];
        ia++;
      } else if (b[ib] < a[ia]) {
        result[i] = b[ib];
        ib++;
      } else {
        result[i] = a[ia];
        i++;
        result[i] = b[ib];
        ia++;
        ib++;
      }
    }
    return result;
  }

  /**
   * LeetCode 2. Add Two Numbers
   * 
   * You are given two non-empty linked lists representing two non-negative
   * integers.
   * The digits are stored in reverse order, and each of their nodes contains a
   * single digit.
   * Add the two numbers and return the sum as a linked list.
   * You may assume the two numbers do not contain any leading zero, except the
   * number 0 itself.
   */
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode root = new ListNode();
    ListNode aux = root;

    // add both lists
    while (l1 != null && l2 != null) {
      aux.next = new ListNode(l1.val + l2.val);
      // start a new round
      l1 = l1.next;
      l2 = l2.next;
      aux = aux.next;
    }
    if (l1 != null) {
      aux.next = l1;
    }
    if (l2 != null) {
      aux.next = l2;
    }

    // reduce to one digit
    aux = root;
    int remainder = 0;
    while (aux.next != null) {
      aux = aux.next;
      aux.val += remainder;
      if (aux.val > 9) {
        aux.val -= 10;
        remainder = 1;
      } else {
        remainder = 0;
      }
    }
    if (remainder == 1) {
      aux.next = new ListNode(1);
    }
    return root.next;
  }

  /**
   * LeetCode 704. Binary Search
   * 
   * Given an array of integers nums which is sorted in ascending order, and an
   * integer target, write a function to search target in nums. If target exists,
   * then return its index. Otherwise, return -1.
   * You must write an algorithm with O(log n) runtime complexity.
   */
  public int search(int[] nums, int target) {
    int start = 0;
    int end = nums.length - 1;
    int index = 0;
    while (start <= end) {
      index = (start + end) / 2;
      if (target > nums[index]) {
        start = index + 1;
      } else if (target < nums[index]) {
        end = index - 1;
      } else {
        return index;
      }
    }
    return -1;
  }

  /**
   * LeetCode 278. First Bad Version
   * 
   * You are a product manager and currently leading a team to develop a new
   * product.
   * Unfortunately, the latest version of your product fails the quality check.
   * Since each version is developed based on the previous version, all the
   * versions after a bad version are also bad.
   * Suppose you have n versions [1, 2, ..., n] and you want to find out the first
   * bad one, which causes all the following ones to be bad.
   * You are given an API bool isBadVersion(version) which returns whether version
   * is bad.
   * Implement a function to find the first bad version.
   * You should minimize the number of calls to the API.
   */
  public int firstBadVersion(int n) {
    int firstBadVersion = n;
    int l = 1;
    int r = n;
    while (l <= r) {
      int test;
      if (l + r <= 0) {
        test = (l == r) ? l : (l / 2) + (r / 2);
      } else {
        test = (l + r) / 2;
      }
      if (isBadVersion(test)) {
        firstBadVersion = test;
        r = test - 1;
      } else {
        l = test + 1;
      }
    }
    return firstBadVersion;
  }

  /**
   * LeetCode 35. Search Insert Position
   * 
   * Given a sorted array of distinct integers and a target value, return the
   * index if the target is found.
   * If not, return the index where it would be if it were inserted in order.
   * You must write an algorithm with O(log n) runtime complexity.
   */
  public int searchInsert(int[] nums, int target) {
    int l = 0;
    int r = nums.length - 1;
    int mid = -1;
    while (l <= r) {
      mid = (l + r) / 2;
      if (target > nums[mid]) {
        l = ++mid;
      } else if (target < nums[mid]) {
        r = mid - 1;
      } else {
        return mid;
      }
    }
    return mid;
  }

  /**
   * LeetCode 977. Squares of a Sorted Array
   * 
   * Given an integer array nums sorted in non-decreasing order, return an array
   * of the squares of each number sorted in non-decreasing order.
   */
  public int[] sortedSquares(int[] nums) {
    int[] ans = new int[nums.length];
    int ansPos = nums.length - 1;
    int l = 0;
    int r = nums.length - 1;

    while (l <= r) {
      // first compare left most and right most number to find the next greater
      // number.
      int lNum = Math.abs(nums[l]);
      int rNum = Math.abs(nums[r]);
      if (lNum >= rNum) {
        l++;
      } else {
        r--;
      }

      int greaterNum = Math.max(lNum, rNum);
      ans[ansPos--] = greaterNum * greaterNum;
    }

    return ans;
  }

  /**
   * LeetCode 283. Move Zeroes
   * 
   * Given an integer array nums, move all 0's to the end of it while maintaining
   * the relative order of the non-zero elements.
   * Note: you must do this in-place without making a copy of the array.
   */
  public void moveZeroes(int[] nums) {
    int k = 0; // pointer for next non zero elements

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        if (k == i) {
          k++;
        } else {
          int temp = nums[i];
          nums[i] = nums[k];
          nums[k] = temp;
        }
      }
    }
  }

  /**
   * LeetCode 344. Reverse String
   * Write a function that reverses a string. The input string is given as an
   * array of characters s.
   * You must do this by modifying the input array in-place with O(1) extra
   * memory.
   */
  public void reverseString(char[] s) {
    // left most char index
    int l = 0;
    // right most char index
    int r = s.length - 1;
    // while there is at least one char among indexes, change char positions.
    while (l < r) {
      char aux = s[l];
      s[l] = s[r];
      s[r] = aux;
      l++;
      r--;
    }
  }

  /**
   * LeetCode 557. Reverse Words in a String III
   * Given a string s, reverse the order of characters in each word within a
   * sentence while still preserving whitespace and initial word order.
   */
  public String reverseWords(String s) {
    int l = 0;
    int r = 0;
    StringBuilder buffer = new StringBuilder(s.length());
    String[] worlds = s.split(" ");
    for (int i = 0; i < worlds.length; i++) {
      char[] world = worlds[i].toCharArray();
      reverseString(world);
      buffer.append(world);
    }
    return buffer.toString();
  }

  /**
   * LeetCode 876. Middle of the Linked List
   * Given the head of a singly linked list, return the middle node of the linked
   * list
   * 
   * Notes: Definition for singly-linked list.
   * public class ListNode {
   * int val;
   * ListNode next;
   * ListNode() {}
   * ListNode(int val) { this.val = val; }
   * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   * }
   */

  public ListNode middleNode(ListNode head) {
    if (head == null)
      return null;
    ListNode lastNode = head;
    ListNode middleNode = head;
    while (lastNode.next != null) {
      lastNode = lastNode.next;
      middleNode = middleNode.next;
      if (lastNode.next != null) {
        lastNode = lastNode.next;
      }
    }
    return middleNode;
  }

  /**
   * LeetCode 733. Flood Fill
   * 
   * An image is represented by an m x n integer grid image where image[i][j]
   * represents the pixel value of the image.
   * You are also given three integers sr, sc, and color. You should perform a
   * flood fill on the image starting from the pixel image[sr][sc].
   * To perform a flood fill, consider the starting pixel, plus any pixels
   * connected 4-directionally to the starting pixel of the same color as the
   * starting pixel,
   * plus any pixels connected 4-directionally to those pixels (also with the same
   * color), and so on.
   * Replace the color of all of the aforementioned pixels with color.
   * 
   */
  public int[][] floodFill(int[][] image, int sr, int sc, int color) {
    class Point {
      int x;
      int y;

      Point(int x, int y) {
        this.x = x;
        this.y = y;
      }
    }
    int collorToChange = image[sr][sc];
    if (collorToChange == color) {
      return image;
    }

    List<Point> pointsToVisit = new ArrayList<>();
    pointsToVisit.add(new Point(sr, sc));
    while (!pointsToVisit.isEmpty()) {
      Point p = pointsToVisit.remove(0);
      image[p.x][p.y] = color;
      if (p.x - 1 >= 0 && image[p.x - 1][p.y] == collorToChange) {
        pointsToVisit.add(new Point(p.x - 1, p.y));
      }
      if (p.x + 1 < image.length && image[p.x + 1][p.y] == collorToChange) {
        pointsToVisit.add(new Point(p.x + 1, p.y));
      }
      if (p.y - 1 >= 0 && image[p.x][p.y - 1] == collorToChange) {
        pointsToVisit.add(new Point(p.x, p.y - 1));
      }
      if (p.y + 1 < image[p.x].length && image[p.x][p.y + 1] == collorToChange) {
        pointsToVisit.add(new Point(p.x, p.y + 1));
      }
    }
    return image;
  }

  /**
   * Methods needed to compile locally
   */

  /**
   * This method is here just to let questions compile.
   */
  private boolean isBadVersion(int num) {
    throw new UnsupportedOperationException("This code is implmented within leetcode platform.");
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