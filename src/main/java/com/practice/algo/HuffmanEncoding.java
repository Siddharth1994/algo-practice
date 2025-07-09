package com.practice.algo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class HuffmanEncoding {
    public static void main(String[] args){
        HuffmanEncoder he = new HuffmanEncoder();
        String encodedText=he.encode("abaceba");
        System.out.println(encodedText);
        System.out.println(he.decode(encodedText));
    }
}

class HuffmanEncoder{
    static class Node{
        char c;long reps;Node left,right;
        public Node(char c, Node right, Node left, long reps) {
            this.c = c;
            this.right = right;
            this.left = left;
            this.reps = reps;
        }
    }
    private Node root;
    public String encode(String text){
        Map<Character,String> codeMap=new HashMap<>();
        configure(text,codeMap);
        StringBuilder result = new StringBuilder();
        for(int i=0;i<text.length();i++)
            result.append(codeMap.get(text.charAt(i)));
        return result.toString();
    }

    public String decode(String encodedText){
        StringBuilder result = new StringBuilder();
        traverse(encodedText, root,0,result);
        return result.toString();
    }

    private void configure(String text, Map<Character,String> codeMap){
        this.root=construct(text);
        populateCode(root ,"", codeMap);
    }

    private void traverse(String encodedText, Node node, int index, StringBuilder result){
         if(node.left ==null && node.right==null){
            result.append(node.c);
            traverse(encodedText, root,index, result);
        } else if(index!=encodedText.length()){
            if(encodedText.charAt(index) =='0') traverse(encodedText, node.left,index+1, result);
            else traverse(encodedText, node.right,index+1,result);
        }
    }

    private Node construct(String text){
        Map<Character,Long> map = (text.chars().boxed()
                .collect(Collectors.groupingBy(c -> (char) c.intValue(), Collectors.counting())));
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingLong(a -> a.reps));
        map.forEach((key, value) -> q.add(new Node(key, null, null, value)));
        while(q.size()!=1){
            Node a=q.poll();
            Node b=q.poll();
            Node c= new Node('-', a, b, a.reps + b.reps);
            q.add(c);
        }
        return q.poll();
    }

    private void populateCode(Node node, String code, Map<Character,String> codeMap){
        if(node.left ==null && node.right==null)
            codeMap.put(node.c, code);
        else if(node.left!=null && node.right!=null){
            populateCode(node.left,code+"0",codeMap);
            populateCode(node.right,code+"1",codeMap);
        }
    }
}
