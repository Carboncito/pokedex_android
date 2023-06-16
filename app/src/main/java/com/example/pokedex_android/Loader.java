package com.example.pokedex_android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class Loader {
    Activity activity;
    AlertDialog dialog;
    ImageView imageView;

    public Loader(Activity currentActivity) {
        activity = currentActivity;
    }

    public void startLoader() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.loader, null));
        builder.setCancelable(true);

        dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
                imageView = dialog.getWindow().findViewById(R.id.pokeball_loader);

                rotateImage(imageView);
            }
        });

        dialog.show();
    }

    public void dismissDialog() {
        dialog.dismiss();
    }

    private void rotateImage(View view){
        RotateAnimation animation = new RotateAnimation(
                0,
                360,
                RotateAnimation.RELATIVE_TO_SELF,
                0.5f,
                RotateAnimation.RELATIVE_TO_SELF,
                0.5f
        );

        animation.setDuration(1500);
        animation.setRepeatCount(Animation.INFINITE);
//        animation.setStartOffset(500);
//        animation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(animation);
    }
}
