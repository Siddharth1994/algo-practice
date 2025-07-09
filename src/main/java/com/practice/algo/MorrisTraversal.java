package com.practice.algo;


import com.practice.algo.Util.Node;

import java.util.ArrayList;
import java.util.List;

public class MorrisTraversal {
    public List<Integer> inorderTraversal(Node root){
        List<Integer> res = new ArrayList<>();
        Node cur=root;
        while(cur!=null){
            if(cur.left==null){
                res.add(cur.val);
                cur=cur.right;
            } else{
                Node predecessor = findPredecessor(cur);
                if(predecessor.right==null){
                    predecessor.right=cur;
                    cur=cur.left;
                } else{
                    predecessor.right=null;
                    res.add(cur.val);
                    cur=cur.right;
                }
            }
        }
        return res;
    }

    private Node findPredecessor(Node start){
        Node node=start.left;
        while(node.right!=null && !node.right.equals(start)) node=node.right;
        return node;
    }
}
