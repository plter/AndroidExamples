package top.yunp.imageviewdatabinding;

import android.databinding.ObservableInt;
import android.view.View;

import top.yunp.imageviewdatabinding.databinding.ActivityMainBinding;

/**
 * Created by plter on 5/28/17.
 */

public class ActivityMainController {

    private ActivityMainBinding binding;
    private final ObservableInt imageId = new ObservableInt(android.R.drawable.ic_media_play);

    public ActivityMainController(ActivityMainBinding binding) {
        this.binding = binding;
        binding.setPresenter(this);
        binding.setImageId(imageId);
    }

    public void onImageViewClickedHandler(View v) {
        imageId.set(imageId.get() == android.R.drawable.ic_media_play ? android.R.drawable.ic_media_pause : android.R.drawable.ic_media_play);
    }
}
