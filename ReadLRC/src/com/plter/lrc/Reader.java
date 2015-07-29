package com.plter.lrc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by plter on 7/17/15.
 */
public class Reader {

    public Reader(String str){
        int strLen = str.length();
        char ch;
        StringBuilder currentStr = new StringBuilder();
        List<Integer> timeTags = new ArrayList<>();

        for (int i=0;i<strLen;i++){
            ch = str.charAt(i);

            switch (ch){
                case '[':
                    //find minute
                    clearStringBuilder(currentStr);
                    for (int a=i+1;a<strLen;a++){
                        ch = str.charAt(a);
                        if (ch!=':'){
                            currentStr.append(ch);
                        }else {
                            i = a;
                            break;
                        }
                    }
                    int min = 0;
                    try {
                         min = Integer.parseInt(currentStr.toString());
                    }catch (RuntimeException e){
                        break;
                    }

                    //find second
                    clearStringBuilder(currentStr);
                    for (int b=i+1;b<strLen;b++){
                        ch = str.charAt(b);

                        if (ch!='.'&&ch!=']'){
                            currentStr.append(ch);
                        }else {
                            i = b;
                            break;
                        }
                    }

                    int sec = 0;
                    try {
                        sec = Integer.parseInt(currentStr.toString());
                    }catch (RuntimeException e){
                        break;
                    }

                    //find next ']'
                    for (int c=i;c<strLen;c++){
                        if (str.charAt(c)==']'){
                            i=c;

                            clearStringBuilder(currentStr);
                            timeTags.add(min*60+sec);
                            break;
                        }
                    }
                    break;
                case '\n':
                    if (timeTags.size()>0) {
                        for (int time : timeTags) {
                            lrcMap.put(time, currentStr.toString());
                        }
                        timeTags.clear();
                    }
                    break;
                default:
                    currentStr.append(ch);
                    break;
            }
        }
    }

    private void clearStringBuilder(StringBuilder str){
        str.delete(0,str.length());
    }

    public String get(int sec){
        return lrcMap.get(sec);
    }

    public boolean contains(int sec){
        return lrcMap.containsKey(sec);
    }

    private Map<Integer,String> lrcMap = new HashMap<>();
}
