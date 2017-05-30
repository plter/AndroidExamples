package top.yunp.startactivitywithpermission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Toast;

import top.yunp.common.AppPermissons;

/**
 * Created by plter on 5/28/17.
 */

public class Presenter {


    private MainActivity mainActivity;

    public Presenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void btnStartBActivityClicked(View v) {

        v.getContext().startActivity(new Intent("top.yunp.anotherapp.intent.action.BActivity"));
    }
}
