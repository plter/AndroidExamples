package com.plter.translate3d;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A placeholder fragment containing a simple view.
 */
public class BackgroundFragment extends Fragment{




    public BackgroundFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_background, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        getFragmentManager().beginTransaction().add(R.id.fragment,new AppMainFragment()).commit();
    }
}
