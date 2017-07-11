package top.yunp.draganddrop;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.View;

import top.yunp.draganddrop.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnDragListener {

    private ActivityMainBinding binding;
    private int buttonHeight = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.root.setOnDragListener(this);
        addViews();
    }

    private void addViews() {
        MyButton btn;
        for (int i = 0; i < 5; i++) {
            btn = new MyButton(this);
            btn.setText("Button " + i);
            btn.setLongClickable(true);
            btn.setOnLongClickListener(this);
            binding.root.addView(btn, -1, buttonHeight);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        view.startDrag(null, new View.DragShadowBuilder(view), view, 0);
        return true;
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DROP:
                System.out.println("ACTION_DROP");

                View v = (View) dragEvent.getLocalState();
                int index = (int) (dragEvent.getY() / buttonHeight);
                if (index >= binding.root.getChildCount()) {
                    index = binding.root.getChildCount() - 1;
                }

                recordAllButtonsPositionY();
                binding.root.removeView(v);
                binding.root.addView(v, index);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                moveAllButtonToPosition();
                break;
        }

        return true;
    }


    private void recordAllButtonsPositionY() {
        for (int i = 0; i < binding.root.getChildCount(); i++) {
            ((MyButton) binding.root.getChildAt(i)).recordPositionY();
        }
    }

    private void moveAllButtonToPosition() {
        for (int i = 0; i < binding.root.getChildCount(); i++) {
            ((MyButton) binding.root.getChildAt(i)).moveHereFromRecordedPosition();
        }
    }
}
