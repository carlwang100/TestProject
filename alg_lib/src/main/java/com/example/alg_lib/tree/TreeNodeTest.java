package com.example.alg_lib.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;


public class TreeNodeTest {

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    private TreeNode root;

    /**
     * 递归
     * 中序遍历根节点
     */
    public void inOrderTraversalRecursion(){
        inOrderTraversalRecursion(root);

        System.out.println();
    }

    private int isDes = 0;

    /**
     * 递归
     * 中序遍历
     * @param node
     */
    protected void inOrderTraversalRecursion(TreeNode node){
        if (node==null){
            return;
        }
        inOrderTraversalRecursion(node.getlNode());
        System.out.print(node.getData()+" ");
        if (isDes++ == 2){
            System.out.println("----------->>>>>>>>" + node.getData());
        }
        inOrderTraversalRecursion(node.getrNode());
    }


    /**
     * 循环
     * 中序遍历根节点
     */
    public void inOrderTraversalLoop(){
        inOrderTraversalLoop(root);
        System.out.println();
    }

    /**
     * 循环
     * 中序遍历
     * @param node
     */
    protected void inOrderTraversalLoop(TreeNode node){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = node;
        TreeNode temp;
        // 一直往左找，同时沿线的节点放入栈，直到最左边的子节点为空
        // 出栈，并打印该节点，然后转向该节点的右子节点
        while (currentNode!=null || !stack.isEmpty()){
            while (currentNode!=null){
                stack.push(currentNode);
                currentNode = currentNode.getlNode();
            }
            if (!stack.isEmpty()){
                temp = stack.pop();
                System.out.print(temp.getData() + " ");
                currentNode = temp.getrNode();
            }
        }
    }

    int count =0;

    //前序的递归实现
    public void preOrderTraversal(){
        preOrderTraversal(root);
        System.out.println();
    }

    public void preOrderTraversal(TreeNode node){
        if (node == null)return;
        System.out.print(node.getData() + " ");
        preOrderTraversal(node.getlNode());
        preOrderTraversal(node.getrNode());
    }


   public void preOrderTraversalLoop(){
       preOrderTraversalLoop(root);
   }

   /**
    * 树的遍历，是用栈去实现的
    * */
   public void preOrderTraversalLoop(TreeNode node){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = node;
        TreeNode temp;
        while(currentNode != null || !stack.isEmpty()){
            while (currentNode != null){
                System.out.print(currentNode.getData() + " ");
                stack.push(currentNode);
                currentNode = currentNode.getlNode();
            }
            if (!stack.isEmpty()){
                temp = stack.pop();
                currentNode = temp.getrNode();
            }
        }
   }

    //获取二叉树的高度
    public int heigh(){
        int h = heigh(root);
        System.out.println("");
        System.out.println(h);
        return h;
    }

    //获取以某节点为子树的高度
    public int heigh(TreeNode node){
        if(node==null){
            return 0;
           }//递归结束，空子树高度为0
            //递归获取左子树高度
            int l = heigh(node.getlNode());
            //递归获取右子树高度
            int r = heigh(node.getrNode());
            //高度应该算更高的一边，（+1是因为要算上自身这一层）
            return l>r? (l+1):(r+1);
        }


     //确认某串序列是否是二叉搜索树的后序排列
     public boolean verifySequenceOfBST(int[] seq){
       if (seq == null || seq.length == 0)return false;
       return verifySequenceOfBST(seq, 0, seq.length -1 );
     }

     public boolean verifySequenceOfBST(int[] seq, int start, int end){
       if (start >= end){
           return true;
       }
       int root = seq[end];

       int index = start;
       //查找第一个大于根节点的位置
       while (index  < end && seq[index] < root){
           index ++;
       }
       //存储第一个大于根节点的位置
       int temp = index;

       //同时要保证right后面所有的数字都大于根
         while (seq[index] > root){
             index++;
         }
         //表明右边的不是所有的都大于root
         if (index != end){
             return false;
         }
         index  = temp;

       return verifySequenceOfBST(seq, start, index - 1) && verifySequenceOfBST(seq, index, end -1 );
     }

     //层次遍历打印
     public void printFromRoot(TreeNode root){
         Queue<TreeNode> queue = new LinkedList<>();
         queue.add(root);
         while (!queue.isEmpty()){
             TreeNode node = queue.remove();
             System.out.println(node.data);
             if (node.lNode != null){
                 queue.add(node.lNode);
             }
             if (node.rNode != null){
                 queue.add(node.rNode);
             }
         }
     }


    }







