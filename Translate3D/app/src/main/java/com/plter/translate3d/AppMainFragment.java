package com.plter.translate3d;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

/**
 * Created by plter on 7/30/15.
 */
public class AppMainFragment extends Fragment implements Animation.AnimationListener, View.OnClickListener, FragmentManager.OnBackStackChangedListener {


    private View root;
    private Translation3D animToHide = new Translation3D(0,90,300,true);
    private Translation3D animToShow = new Translation3D(90,0,300,false);

    public AppMainFragment(){
        animToHide.setAnimationListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_app_main, container, false);

        root.findViewById(R.id.btnOpenConfigPanel).setOnClickListener(this);
        getFragmentManager().addOnBackStackChangedListener(this);
        return root;
    }

    private void startHide(){
        root.startAnimation(animToHide);
    }

    private void startShow(){
        root.setVisibility(View.VISIBLE);
        root.startAnimation(animToShow);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        root.setVisibility(View.INVISIBLE);
        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment, new ConfigPanelFragment()).commit();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOpenConfigPanel:
                startHide();
                break;
        }
    }

    @Override
    public void onBackStackChanged() {
        if (getFragmentManager().findFragmentById(R.id.fragment)==this){
            startShow();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getFragmentManager().removeOnBackStackChangedListener(this);
    }
}
