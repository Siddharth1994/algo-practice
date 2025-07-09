package com.practice.algo;

import java.util.HashMap;
import java.util.Map;

public class RobinKarp {
    public boolean patternMatch(String text, String pattern){
        int count=0, i, m=text.length(), n=pattern.length();
        long patternHashVal=0, textHashVal=0, mul;

        Map<Character,Integer> charMap=new HashMap<>();
        for(i=0;i<text.length();i++){
            if(!charMap.containsKey(text.charAt(i))) charMap.put(text.charAt(i), ++count);
        }

        for(i=0;i<n;i++){
            patternHashVal+= (long) (charMap.get(pattern.charAt(i))*Math.pow(count,n-1-i));
            textHashVal+=(long) (charMap.get(text.charAt(i))*Math.pow(count,n-1-i));
        }

        if(patternHashVal==textHashVal) return true;
        mul= (long) Math.pow(count,n-1);
        for(;i<m;i++){
            textHashVal=(textHashVal-charMap.get(text.charAt(i-n))*mul)*count+charMap.get(text.charAt(i));
            if(patternHashVal==textHashVal) return true;
        }
        return false;
    }

}
