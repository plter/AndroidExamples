/**
 * Created by plter on 7/17/15.
 */
public class Main {

    public static void main(String[] args){

        for (int i=1;i<=9;i++){
            for (int j=1;j<=i;j++){
                System.out.print(String.format("%d*%d=%d\t",i,j,i*j));
            }
            System.out.println();
        }

    }
}
