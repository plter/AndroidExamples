package com.plter.fragmenttransaction;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {


    private RadioButton rbAnimTypeCustom,rbAnimTypeOpen,rbAnimTypeFade;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        root.findViewById(R.id.btnShowAnotherFragment).setOnClickListener(this);
        rbAnimTypeCustom = (RadioButton) root.findViewById(R.id.animTypeCustom);
        rbAnimTypeOpen = (RadioButton) root.findViewById(R.id.animTypeOpen);
        rbAnimTypeFade = (RadioButton) root.findViewById(R.id.animTypeFade);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnShowAnotherFragment:
                if (rbAnimTypeCustom.isChecked()) {
                    getFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.translate_in, R.anim.translate_out, R.anim.translate_in, R.anim.translate_out)
                            .addToBackStack(null)
                            .replace(R.id.fragment, new AnotherFragment())
                            .commit();
                }else if (rbAnimTypeOpen.isChecked()){
                    getFragmentManager()
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(null)
                            .replace(R.id.fragment,new AnotherFragment())
                            .commit();
                }else if (rbAnimTypeFade.isChecked()){
                    getFragmentManager()
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null)
                            .replace(R.id.fragment,new AnotherFragment())
                            .commit();
                }
                break;
        }
    }
}
