package com.practice.model;

public class TreeNode {
    Integer val;
    TreeNode left,right;
    public static TreeNode createNode(Integer val, TreeNode left, TreeNode right){
        TreeNode node = new TreeNode();
        node.val=val;
        node.left=left;
        node.right=right;
        return node;
    }
}
