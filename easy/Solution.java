package easy;

public class Solution {
  
  /**
   * LeetCode 704. Binary Search
   * 
   * Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.
   * You must write an algorithm with O(log n) runtime complexity.
   */
  public int search(int[] nums, int target) {
    int start = 0;
    int end = nums.length-1;
    int index = 0;
    while (start <= end) {
      index = (start + end)/2; 
      if (target > nums[index]) {
        start = index+1;
      } else if (target < nums[index]){
        end = index-1;
      } else {
        return index;
      }
    }
    return -1;
  }


  /**
   * LeetCode 278. First Bad Version
   * 
   * You are a product manager and currently leading a team to develop a new product. 
   * Unfortunately, the latest version of your product fails the quality check. 
   * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
   * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
   * You are given an API bool isBadVersion(version) which returns whether version is bad. 
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
        test = (l==r)? l: (l / 2) + (r / 2);
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
   * Given a sorted array of distinct integers and a target value, return the index if the target is found. 
   * If not, return the index where it would be if it were inserted in order.
   * You must write an algorithm with O(log n) runtime complexity.
   */
  public int searchInsert(int[] nums, int target) {
    int l = 0;
    int r = nums.length-1;
    int mid = -1;
    while(l<=r) {
      mid = (l+r)/2;
      if (target > nums[mid]) {
        l = ++mid;
      } else if (target < nums[mid]) {
        r = mid-1;
      } else {
        return mid;
      }
    }
    return mid;
  }

  /**
   * LeetCode 977. Squares of a Sorted Array
   * 
   * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
   */
  public int[] sortedSquares(int[] nums) {
    int[] ans = new int[nums.length];    
    int ansPos= nums.length-1;
    int l = 0;
    int r = nums.length-1;
    
    while(l <= r){
      // first compare left most and right most number to find the next greater number.
      int lNum = Math.abs(nums[l]);
      int rNum = Math.abs(nums[r]);
      if (lNum >= rNum){
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
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * Note: you must do this in-place without making a copy of the array.
 */
  public void moveZeroes(int[] nums) {        
    int k = 0; // pointer for next non zero elements
    
    for(int i = 0 ; i < nums.length ; i++){
      if(nums[i] != 0) {
          if (k==i) {
            k++;
          } else{
            int temp = nums[i];
            nums[i] = nums[k];
            nums[k] = temp;
          }
        }
    }    
}


  /**
   * This method is here just to let questions compile.
   */
  private boolean isBadVersion(int num){
    throw new UnsupportedOperationException("This code is implmented within leetcode platform.");
  }

}