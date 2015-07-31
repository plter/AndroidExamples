package com.plter.fragmentflip3d;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by plter on 7/30/15.
 */
public class FragmentImage extends Fragment implements View.OnTouchListener, View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_image,container,false);
        root.setOnTouchListener(this);
        root.findViewById(R.id.btnBack).setOnClickListener(this);
        return root;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                getFragmentManager().popBackStack();
                break;
        }
    }
}
