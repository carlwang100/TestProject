package com.example.alg_lib.tree;

public class TreeNode {

    public int data;
    public TreeNode lNode;
    public TreeNode rNode;

    public TreeNode(int x){
        data = x;
    }


    public TreeNode getlNode() {
        return lNode;
    }

    public void setlNode(TreeNode lNode) {
        this.lNode = lNode;
    }

    public TreeNode getrNode() {
        return rNode;
    }

    public void setrNode(TreeNode rNode) {
        this.rNode = rNode;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

}
