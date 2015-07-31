package com.plter.fragmenttransaction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by plter on 7/30/15.
 */
public class AnotherFragment extends Fragment implements View.OnTouchListener, View.OnClickListener {


    private View root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.another_fragment,container,false);

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
