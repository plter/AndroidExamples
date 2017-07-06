package top.yunp.draganddrop;

import android.content.Context;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

/**
 * Created by plter on 7/6/17.
 */

public class MyButton extends Button {

    private float recordedPositionY = 0;

    public MyButton(Context context) {
        super(context);
    }

    public void recordPositionY() {
        recordedPositionY = getY();
    }

    public void moveHereFromRecordedPosition() {
        TranslateAnimation ta = new TranslateAnimation(0, 0, recordedPositionY - getY(), 0);
        ta.setDuration(300);
        startAnimation(ta);
    }
}
