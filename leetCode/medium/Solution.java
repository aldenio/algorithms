package leetCode.medium;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

  /**
   * 8. String to Integer (atoi)
   * 
   * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
   * 
   * The algorithm for myAtoi(string s) is as follows:
   * Read in and ignore any leading whitespace.
   * Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. 
   * This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
   * Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
   * Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. 
   * Change the sign as necessary (from step 2).
   * If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. 
   * Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
   * Return the integer as the final result.
   * 
   * Note:
   *  Only the space character ' ' is considered a whitespace character.
   *  Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
   */
  public int myAtoi(String s) {
    int i=0;
    long signal=1;
    
    //search for the signal
    for(; i < s.length(); i++){
      char c = s.charAt(i);
      if (c == ' '){
        continue;
      } else if (c == '-') {
        signal = -1;
        i++;          
      } else if (c == '+'){
        signal = 1;
        i++;          
      } 
      break;        
    }
    
   // collect the digits
    StringBuilder builder = new StringBuilder(s.length());
    builder.append('0');
    for(; i < s.length(); i++){
      char c = s.charAt(i);
      if(c >= '0' && c <= '9'){
        builder.append(c);
      } else {
        break;
      }        
    }
    
    // treat values greater than integer
    BigInteger bigValue = new BigInteger(builder.toString()).multiply(BigInteger.valueOf(signal));      
    if (bigValue.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0){
      return Integer.MAX_VALUE;
    }else if (bigValue.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0){
      return Integer.MIN_VALUE;
    } else {
      return bigValue.intValue();
    }
  }

  /**
   * 7. Reverse Integer
   * 
   * Given a signed 32-bit integer x, return x with its digits reversed. 
   * If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
   * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
   */
  public int reverse(int x) {
    int signal = (x < 0)? -1:1;
    long y = 0;
    x = Math.abs(x);
    while(x > 0){
      y = (y*10) + x % 10;
      x /= 10;
      System.out.println("x="+x+", y="+y);
    }
    if (y> Integer.MAX_VALUE) {
      return 0;
    } else {
      return (int) y * signal;      
    }
  }


  /**
   * 695. Max Area of Island
   * 
   * You are given an m x n binary matrix grid. An island is a group of 1's
   * (representing land) connected 4-directionally (horizontal or vertical.)
   * You may assume all four edges of the grid are surrounded by water.
   * The area of an island is the number of cells with a value 1 in the island.
   * Return the maximum area of an island in grid. If there is no island, return 0
   */
  public int maxAreaOfIsland(int[][] grid) {
    int max = 0;
    for (int x = 0; x < grid.length; x++) {
      for (int y = 0; y < grid[x].length; y++) {
        if (grid[x][y] == 1) {
          int size = islandMeasureAndMark(grid, x, y);
          max = (size > max) ? size : max;
        }
      }
    }
    return max;
  }

  private int islandMeasureAndMark(int[][] grid, int x, int y) {
    class Point {
      int x;
      int y;

      Point(int x, int y) {
        this.x = x;
        this.y = y;
      }
    }

    int size = 0;
    List<Point> pointsToVisit = new ArrayList<>();
    pointsToVisit.add(new Point(x, y));
    while (!pointsToVisit.isEmpty()) {
      Point p = pointsToVisit.remove(0);
      if (grid[p.x][p.y] == 2){
        continue;
      }
      grid[p.x][p.y] = 2;
      size++;
      if (p.x - 1 >= 0 && grid[p.x - 1][p.y] == 1) {
        pointsToVisit.add(new Point(p.x - 1, p.y));
      }
      if (p.x + 1 < grid.length && grid[p.x + 1][p.y] == 1) {
        pointsToVisit.add(new Point(p.x + 1, p.y));
      }
      if (p.y - 1 >= 0 && grid[p.x][p.y - 1] == 1) {
        pointsToVisit.add(new Point(p.x, p.y - 1));
      }
      if (p.y + 1 < grid[p.x].length && grid[p.x][p.y + 1] == 1) {
        pointsToVisit.add(new Point(p.x, p.y + 1));
      }
    }
    return size;
  }

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
    // toRemove will point to the node exactly before the node to be removed, so
    // remove it.
    toRemove.next = toRemove.next.next;
    return head;
  }

  /**
   * LeetCode 3. Longest Substring Without Repeating Characters
   * 
   * Given a string s, find the length of the longest substring without repeating
   * characters.
   */
  public int lengthOfLongestSubstring(String s) {
    int longest = 0;
    StringBuilder buff = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      String currentLetter = String.valueOf(s.charAt(i));
      int pos = buff.indexOf(currentLetter);
      if (pos >= 0) {
        buff.delete(0, pos + 1);
      }
      buff.append(currentLetter);
      longest = (buff.length() > longest) ? buff.length() : longest;
    }
    return longest;
  }

  /**
   * LeetCode 567. Permutation in String
   * 
   * Given two strings s1 and s2, return true if s2 contains a permutation of s1,
   * or false otherwise.
   * In other words, return true if one of s1's permutations is the substring of
   * s2.
   * Constraints:
   * 1 <= s1.length, s2.length <= 10e4
   * s1 and s2 consist of lowercase English letters.
   */
  public boolean checkInclusion(String s1, String s2) {
    // s1 doesn't fit in s2
    if (s1.length() > s2.length())
      return false;

    // sort the s1 char array to search
    char[] s2Array = s2.toCharArray();
    char[] s1Array = s1.toCharArray();
    Arrays.sort(s1Array);

    char[] aux = new char[s1.length()];

    for (int i = 0; i <= s2.length() - s1.length(); i++) {
      if (Arrays.binarySearch(s1Array, s2.charAt(i)) >= 0) {
        System.arraycopy(s2Array, i, aux, 0, aux.length);
        Arrays.sort(aux);
        if (Arrays.equals(s1Array, aux)) {
          return true;
        }
      }
    }
    return false;
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