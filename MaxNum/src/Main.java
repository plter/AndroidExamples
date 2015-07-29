/**
 * Created by plter on 7/17/15.
 */
public class Main {


    public static void main(String[] args){
        new Main();
    }


    public Main(){
        System.out.println(max(2,3,5,9,100,1,-4,-5));
    }

    public int max(int ... args){
        int maxNum = Integer.MIN_VALUE;

        for (int i:args){
            maxNum = maxNum>i?maxNum:i;
        }

        return maxNum;
    }
}
