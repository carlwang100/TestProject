package com.example.alg_lib;

import com.example.alg_lib.link.LinkTest;
import com.example.alg_lib.threadlocal.ThreadLocalTest;
import com.example.alg_lib.threadlocal.ThreadTest;
import com.example.alg_lib.threadlocal.VolatileTest;
import com.example.alg_lib.tree.TreeNode;
import com.example.alg_lib.tree.TreeNodeTest;


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
        testVolatile();
    }

    private static void testVolatile() {
        VolatileTest volatileTest = new VolatileTest();
        volatileTest.testVolitale();
    }

    private static void testThreadLocal() {
        new ThreadLocalTest().testLocal();
    }

    private static void testThread() {
        ThreadTest run = new ThreadTest();
        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);
//        Thread t3 = new Thread(run);
//        Thread t4 = new Thread(run);
        t1.start();
        t2.start();
//        t3.start();
//        t4.start();
    }


    private static void operateHashMap() {
        HashMapTest hashMapTest = new HashMapTest();
        hashMapTest.testTraversal();
    }


    // 查找数组中重复的数字
    private static void duplicate(int[] numbers) {
        if (numbers == null || numbers.length == 0) return;
        for (int i = 0; i < numbers.length; i++){
            while (numbers[i] != i){//第一步
                if (numbers[i] == numbers[numbers[i]]){//第二步
                    System.out.println("重复的数字-----> " + numbers[i]);
                    break;
                }
                //第三步
                int tmp = numbers[i];
                numbers[i] = numbers[tmp];
                numbers[tmp] = tmp;
            }
        }
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
        treeNodeTest.inOrderTraversalRecursion();
        treeNodeTest.heigh();

        int[] a = {11,12,15,13,21,30,23,16};
        System.out.println("是否是二叉搜索树的后序排列----->>>>" + treeNodeTest.verifySequenceOfBST(a));
        treeNodeTest.printFromRoot(root);
//        treeNodeTest.inOrderTraversalLoop();
//        treeNodeTest.preOrderTraversal();
//        treeNodeTest.preOrderTraversalLoop();
//        treeNodeTest.heigh();
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


        node1.setlNode(node2);
        node1.setrNode(node3);
        node2.setlNode(node4);
        node2.setrNode(node5);
        node5.setlNode(node6);

        root.setlNode(node1);

        return root;
    }

    public static void testArray(){
        int[] a = {2,3,2,2,4,4};
        ArrayTest arrayTest = new ArrayTest();
        arrayTest.findMoreThanHalfNum(a);
    }

}
