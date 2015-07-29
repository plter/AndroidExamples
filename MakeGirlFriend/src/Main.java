/**
 * Created by plter on 7/17/15.
 */
public class Main {


    public static void main(String[] args){
        String english = "abcdefghijklmnopqrstuvwxyz";

        for (int i=0;i<1000;i++){
            int nameLength = (int) (Math.floor(Math.random() * 3)+3);
            StringBuilder name = new StringBuilder();
            for (int j=0;j<nameLength;j++){
                name.append(english.charAt((int) (Math.random()*english.length())));
            }

            System.out.println(new GirlFriend(name.toString(), (int) (Math.floor(Math.random()*11)+10)));
        }
    }
}
