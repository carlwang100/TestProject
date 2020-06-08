package com.example.alg_lib.binarysearch;

public class BinarySearchTest {

    //查找第一个值d等于指定值的元素
    private int bSearchForfirstValue(int[] a, int k){
        int low = 0;
        int high = a.length - 1;
        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if (a[mid] < k){
                low = mid + 1;
            }else if (a[mid] > k){
                high = mid - 1;
            }else {
                if (mid == 0 || a[mid - 1] != k)return mid;
                else {
                    high  = mid - 1;
                }
            }
        }

        return 0;
    }

    //查找旋转数组的指定值
    public int solution(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        while (e >= s){
            int mid = s + (e-s)/2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < nums[e]) {
                if (target < nums[mid] || target > nums[e]) {
                    e = mid - 1;
                } else s = mid + 1;
            } else {
                if (target < nums[s] || target > nums[mid]) {
                    s = mid + 1;
                } else e = mid - 1;
            }
        }
        return -1;
    }

}
