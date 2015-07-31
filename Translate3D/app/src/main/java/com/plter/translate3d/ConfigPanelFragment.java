package com.plter.translate3d;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

/**
 * Created by plter on 7/29/15.
 */
public class ConfigPanelFragment extends Fragment implements View.OnKeyListener, Animation.AnimationListener, View.OnClickListener {


    private Translation3D animToShow = new Translation3D(-90,0,300,false);
    private Translation3D animToHide = new Translation3D(0,-90,300,true);

    private View root;

    public ConfigPanelFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_config_panel,container,false);
        startShow();

        animToHide.setAnimationListener(this);

        root.findViewById(R.id.btnBack).setOnClickListener(this);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        root.setFocusable(true);
        root.setFocusableInTouchMode(true);
        root.requestFocus();
        root.setOnKeyListener(this);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            startHide();
            return true;
        }
        return false;
    }

    private void startHide(){
        root.startAnimation(animToHide);
    }

    private void startShow(){
        root.startAnimation(animToShow);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        getFragmentManager().popBackStack();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                startHide();
                break;
        }
    }
}
