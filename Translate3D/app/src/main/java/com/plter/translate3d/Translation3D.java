package com.plter.translate3d;

import android.graphics.Camera;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by plter on 7/30/15.
 */
public class Translation3D extends Animation {


    private float from,to,delta;
    private int parentWidth,parentHeight,halfParentWidth,halfParentHeight;
    private Camera camera;
    private boolean farAway = true;

    public Translation3D(float from,float to,long duration,boolean farAway){
        this.from = from;
        this.to = to;
        delta = this.to-this.from;
        this.farAway = farAway;
        setDuration(duration);
    }


    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);

        camera = new Camera();
        this.parentWidth = parentWidth;
        this.parentHeight = parentHeight;

        halfParentHeight = this.parentHeight/2;
        halfParentWidth = this.parentWidth/2;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        camera.save();
        camera.translate(0,0,(farAway?interpolatedTime:1-interpolatedTime)*500);
        camera.rotateY(from + delta * interpolatedTime);


        camera.getMatrix(t.getMatrix());
        t.getMatrix().preTranslate(-halfParentWidth,-halfParentHeight);
        t.getMatrix().postTranslate(halfParentWidth,halfParentHeight);

        camera.restore();
    }
}
