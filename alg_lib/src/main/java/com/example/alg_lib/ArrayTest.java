package com.example.alg_lib;

import sun.misc.FpUtils;

public class ArrayTest {

    //找出占数组绝大多数的那个数字 {2,3,2,2,4,2}
    public void findMoreThanHalfNum(int a[]){
        int count = 1;
        int result =0;
        for (int i = 0; i < a.length; i++){
            if (count == 0){
                result = a[i];
                count = 1;
                continue;
            }
            if (result == a[i]){
                count++;
            }else {
                count--;
            }
        }

        count = 0;
        //判断result出现的次数是否超过了数组的半
        for(int ret : a){
            if (ret == result)count++;
        }
        if (count > a.length/2){
            System.out.println("满足要求的数值是---》》"+ result);
        }else {
            System.out.println("不存在满足要求的数值是---》》");
        }
    }

    //查找数组中缺失的数
    public int findMissedNum(int[] a){
        if (a == null || a.length == 0)return -1;
        int start = 0;
        int end = a.length;
        while(start < end){
            int middle = (start + end) >> 1;
            if (a[middle] != middle){
                if (middle == 0 || a[middle - 1] == middle - 1){
                    System.out.println("缺失的数字是--->> " + middle);
                    return middle;
                }
                end = middle - 1;
            }else {
                start = middle + 1;
            }
        }
        return -1;
    }

    //全排列
    public static void permutation(char[] chars) {
        // 输入较验
        if (chars == null || chars.length < 1) {
            return;
        }
        // 进行排列操作
        permutation(chars, 0);
    }

    /**
     * 求字符数组的排列
     *
     * @param chars 待排列的字符串
     * @param begin 当前处理的位置
     */
    public static void permutation(char[] chars, int begin) {
        // 如果是最后一个元素了，就输出排列结果
        if (chars.length - 1 == begin) {
            System.out.print(new String(chars) + " ");
        } else {
            char tmp;
            // 对当前还未处理的字符串进行处理，每个字符都可以作为当前处理位置的元素
            for (int i = begin; i < chars.length; i++) {
                // 下面是交换元素的位置
                tmp = chars[begin];
                chars[begin] = chars[i];
                chars[i] = tmp;

                // 处理下一个位置
                permutation(chars, begin + 1);

                //前缀换回来，继续做上一个的全排列
                tmp = chars[begin];
                chars[begin] = chars[i];
                chars[i] = tmp;
            }
        }
    }

    //排序数组中去找两数之和
    public static int[] findSum(int[] arr, int s){
        if (arr == null || arr.length <= 0)return null;
        int start = 0;
        int end = arr.length - 1;
        int[] out = new int[2];
        while (start < end){
            int sum = arr[start] + arr[end];
            if (sum == s){
                out[0] = arr[start];
                out[1] = arr[end];
                return out;
            }else if (sum > s){
                end--;
            }else {
                start++;
            }
        }
        return null;
    }

    //滑动窗口的最大值
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }

        int[] maxes = new int[nums.length - k + 1];

        int i, j;
        int maxPos = -1;

        for (i = 0; i <= nums.length - k; ++i) {
            // Ending index of the current window
            j = i + k - 1;

            // new element >= max of last window
            // that means new element is max in the two windows
            // here using >= to make maxPos stay in the windows for a longer time
            if (maxPos != -1 && nums[j] >= nums[maxPos]) {
                maxPos = j;
                maxes[i] = nums[maxPos];
            }
            // new element < max of last window
            // AND the max of last window is also in this window
            // => it means the max of the last window is still the max of this window
            else if (i <= maxPos) {
                maxes[i] = nums[maxPos];
            }
            // new element < max of last window
            // AND the max of last window is not in this window
            // So we do not know which element is the max in this window, we have to scan the window to find it
            else {
                int maxWindow = Integer.MIN_VALUE;
                int maxPosWindow = 0;
                for (int z = i; z <= j; ++z) {
                    if (nums[z] > maxWindow) {
                        maxPosWindow = z;
                        maxWindow = nums[z];
                    }
                }
                maxPos = maxPosWindow;
                maxes[i] = nums[maxPos];
            }
        }
        return maxes;
    }

}
