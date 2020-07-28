package com.example.alg_lib;

import com.example.alg_lib.cache.LruCache;
import com.example.alg_lib.cache.LruCacheTest;
import com.example.alg_lib.leetcode.ArrayTrainning;
import com.example.alg_lib.link.LinkTest;
import com.example.alg_lib.threadlocal.CountDownLatchTest;
import com.example.alg_lib.threadlocal.ThreadLocalTest;
import com.example.alg_lib.threadlocal.ThreadTest;
import com.example.alg_lib.threadlocal.VolatileTest;
import com.example.alg_lib.tree.TreeNode;
import com.example.alg_lib.tree.TreeNodeTest;

import java.util.jar.Attributes;


public class Main {

    static int[] a = {2, 3, 1, 0, 2, 5, 3};

    public static void main(String[] args){
//        duplicate(a);
//        operateLink();
//        operateTree();
//        operateHashMap();
//        testArray();
//        testThread();
//        testThreadLocal();
//        testVolatile();
//        testCountDownLatch();
        testLruCache();
//        printFrom1toNbit(2);
    }

    private static void testCountDownLatch() {
        new CountDownLatchTest().test();
    }


    //打印从1到n的最大n位数
    private static void printFrom1toNbit(int n) {
        // 输入的数字不能为小于1
        if (n < 1) {
            throw new RuntimeException("The input number must larger than 0");
        }
        // 创建一个数组用于打存放值
        int[] arr = new int[n];
        printOneToNthDigits(0, arr);
    }

    private static void printOneToNthDigits(int n, int[] arr) {
       // 说明数组已经装满元素
        if (n >= arr.length) {
            // 可以输出数组的值
            printArray(arr);
        } else {
            for (int i = 0; i <= 9; i++) {
                arr[n] = i;
                printOneToNthDigits(n + 1, arr);
            }
        }
    }

    private static void printArray(int[] arr) {
        // 找第一个非0的元素
        int index = 0;
        while (index < arr.length && arr[index] == 0) {
            index++;
        }

        // 从第一个非0值到开始输出到最后的元素。
        for (int i = index; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        // 条件成立说明数组中有非零元素，所以需要换行
        if (index < arr.length) {
            System.out.println();
        }

    }

    private static void testVolatile() {
        VolatileTest volatileTest = new VolatileTest();
        volatileTest.testVolitale();
    }

    private static void testThreadLocal() {
        new ThreadLocalTest().testLocal();
    }


    static int value = 0;
    static boolean flag = false;

    private static void testThread() {
        ThreadTest run = new ThreadTest();
        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);
        t1.start();
        t2.start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                write();
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                read();
//            }
//        }).start();
    }

    private static void read() {
        if (flag){
            System.out.print("-----》》》" + value * value);
        }
    }

    private static void write() {
        value = 2;
        flag = true;
    }


    private static void operateHashMap() {
        HashMapTest hashMapTest = new HashMapTest();
        hashMapTest.testTraversal();
    }


    // 查找数组中重复的数字
    private static boolean duplicate(int[] numbers) {
        if (numbers == null || numbers.length == 0) return false;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 0 || numbers[i] > numbers.length) return false;
        }
        for (int i = 0; i < numbers.length; i++){
            while (numbers[i] != i){//第一步
                if (numbers[i] == numbers[numbers[i]]){//第二步
                    System.out.println("重复的数字-----> " + numbers[i]);
                    return true;
                }
                //第三步
                int tmp = numbers[i];
                numbers[i] = numbers[tmp];
                numbers[tmp] = tmp;
            }
        }

        return false;
    }


    private static void operateLink() {
        LinkTest linkTest = new LinkTest();
        for (int i = 1; i < 2; i++){
            linkTest.addNode(i);
        }
        linkTest.length(linkTest.head);
//        linkTest.deleteNode(linkTest.head, 3);
          linkTest.reverseLink(linkTest.head);
//        linkTest.removeLastKFromend(linkTest.head, 6);
//        linkTest.hasCycle(linkTest.head);
//        linkTest.findCycleNodeEntry(linkTest.head);
//        linkTest.findMiddleNode(linkTest.head);
    }


    private static void operateTree() {
       TreeNode root =  makeTreeTest();
        TreeNodeTest treeNodeTest = new TreeNodeTest();
        treeNodeTest.setRoot(root);
//        treeNodeTest.inOrderTraversalRecursion();
//        treeNodeTest.heigh();

//        int[] a = {11,12,15,13,21,30,23,16};
//        System.out.println("是否是二叉搜索树的后序排列----->>>>" + treeNodeTest.verifySequenceOfBST(a));
//        treeNodeTest.printFromRoot(root);
//        treeNodeTest.inOrderTraversalLoop();
//        treeNodeTest.preOrderTraversal();
//        treeNodeTest.preOrderTraversalLoop();
//        treeNodeTest.heigh();
//        System.out.println(treeNodeTest.maxDepthWithBSF(root));
        treeNodeTest.priFromTop(root);
    }

    /**
     * 生成测试树
     *        10
     *      /    \
     *    3      12
     *   / \
     *  2  5
     * / \
     * 4  9
     *   /  \
     *   1   4
     * @return node
     */
    public static TreeNode makeTreeTest(){
        TreeNode root = new TreeNode(10);
        root.setrNode(new TreeNode(12));

        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(9);
        TreeNode node6 = new TreeNode(1);
        TreeNode node7 = new TreeNode(4);

        node1.setlNode(node2);
        node1.setrNode(node3);
        node2.setlNode(node4);
        node2.setrNode(node5);
        node5.setlNode(node6);
        node5.setrNode(node7);
        root.setlNode(node1);

        return root;
    }

    public static void testArray(){
//        int[] a = {2,3,2,2,4,4};
//        int[] a = {1,2,3,4,6,7,8,9};
        int[] a = {1,1,1,1,1,1};
//        ArrayTest arrayTest = new ArrayTest();
//        arrayTest.findMoreThanHalfNum(a);
//        arrayTest.findMissedNum(a);
//        ArrayTest.permutation("12".toCharArray());
//        ArrayTest.findSum(a, 9 );
//        ArrayTest.maxSlidingWindow(a, 3);

        ArrayTrainning arrayTrainning = new ArrayTrainning();
        arrayTrainning.numOfSubarrays(a, 1, 1);
    }

    public static void testLruCache(){
        LruCacheTest.lruTest();
    }

    //查找从1-n中1的个数
    public static void findOnes(int n){
        int num = 0;
        for (int i = 0; i < n; i++) {
            int cur = i;
            while(cur != 0){
                num++;
                if (cur % 10 == 1)
                    num++;
                cur = cur / 10;
            }
        }
    }
}
