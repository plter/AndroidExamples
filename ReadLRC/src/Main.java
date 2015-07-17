import com.plter.lrc.Reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by plter on 7/17/15.
 */
public class Main {


    public static void main(String[] args){
        File f = new File("sound.lrc");
        try {
            FileInputStream fileInputStream = new FileInputStream(f);
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            fileInputStream.close();
            String content = new String(bytes,"UTF-8");

            Reader reader = new Reader(content);

            for (int i=0;i<100;i++){

                if (reader.contains(i)) {
                    System.out.print(String.format("\r%s", reader.get(i)));
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
