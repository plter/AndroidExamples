package sample;

import javafx.event.Event;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {


    public TextField tfRegExp;
    public TextArea taTextContent;
    public TextArea taOutput;

    public void btnStartMatchClickedHandler(Event event) {
        taOutput.clear();

        Pattern p = Pattern.compile(tfRegExp.getText());
        Matcher matcher = p.matcher(taTextContent.getText());
        while (matcher.find()){
            taOutput.appendText(matcher.group()+"\n");
        }
    }
}
