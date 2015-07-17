package com.plter.lrc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by plter on 7/17/15.
 */
public class Reader {

    public Reader(String str){
        int strLen = str.length();

        for (int i=0;i<strLen;i++){
            char currentChar = str.charAt(i);

            switch (currentChar){
                case '[':
                    //find minute
                    StringBuilder minStr = new StringBuilder();
                    for (int a=i+1;a<strLen;a++){
                        char ch = str.charAt(a);
                        if (ch!=':'){
                            minStr.append(ch);
                        }else {
                            i = a;
                            break;
                        }
                    }
                    int min = Integer.parseInt(minStr.toString());

                    //find sec
                    StringBuilder secStr = new StringBuilder();
                    for (int b=i+1;b<strLen;b++){
                        char ch = str.charAt(b);

                        if (ch!='.'&&ch!=']'){
                            secStr.append(ch);
                        }else {
                            i = b;
                            break;
                        }
                    }
                    int sec = Integer.parseInt(secStr.toString());

                    //find next ']'
                    for (int c=i;c<strLen;c++){
                        if (str.charAt(c)==']'){
                            i=c;
                            break;
                        }
                    }


                    //find content
                    StringBuilder content = new StringBuilder();
                    for (int d=i+1;d<strLen;d++){
                        char ch = str.charAt(d);
                        if (ch!='\n'){
                            content.append(ch);
                        }else {
                            i=d;
                            break;
                        }
                    }

                    lrcMap.put(min*60+sec,content.toString());
                    break;
            }
        }
    }

    public String get(int sec){
        return lrcMap.get(sec);
    }

    public boolean contains(int sec){
        return lrcMap.containsKey(sec);
    }

    private Map<Integer,String> lrcMap = new HashMap<>();
}
