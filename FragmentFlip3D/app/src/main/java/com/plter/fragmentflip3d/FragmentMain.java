package com.plter.fragmentflip3d;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by plter on 7/30/15.
 */
public class FragmentMain extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);

        root.findViewById(R.id.btnGoNextPage).setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGoNextPage:
                getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.animator.flip_enter, R.animator.flip_exit, R.animator.flip_pop_enter, R.animator.flip_pop_exit)
                        .replace(R.id.fragment, new FragmentImage())
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
