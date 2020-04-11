package com.example.exempleviewflipper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        int[] images = {
                R.drawable.sandwich_1,
                R.drawable.sandwich_2,
                R.drawable.sandwich_3,
                R.drawable.sandwich_4,
                R.drawable.sandwich_5,
                R.drawable.sandwich_6,
                R.drawable.sandwich_7,
        };

        final ViewFlipper viewFlipper = findViewById(R.id.viewFlipper);
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_out));

        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(3000);

        for (int i = 0; i < images.length; i++) {
            ItemView itemView = new ItemView(this);

            int imageId = images[i];
            String imageName = getResources().getResourceName(imageId);

            ImageView imageView = itemView.findViewById(R.id.imageView);
            Picasso.get().load(imageId).into(imageView);

            //TextView textView = itemView.findViewById(R.id.textView);
            //textView.setText(imageName);
            //Log.e("toto", imageName);

            viewFlipper.addView(itemView);
        }

        final Button btnStartStop = findViewById(R.id.btnStartStop);
        btnStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (viewFlipper.isFlipping()) {
                    viewFlipper.stopFlipping();
                    btnStartStop.setText("Start");
                } else {
                    viewFlipper.startFlipping();
                    btnStartStop.setText("Stop");
                }
            }
        });

    }

    private int getDrawableByName(String nomImage) {
        String pkgName = this.getPackageName();
        // Return 0 if not found.
        int resID = this.getResources().getIdentifier(nomImage, "drawable", pkgName);
        //Log.i("MyLog", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }
}
