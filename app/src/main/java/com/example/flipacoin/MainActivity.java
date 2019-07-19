package com.example.flipacoin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random random = new Random();

    ImageView ivCoin;
    Button btnFlip;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        clickEvent();
    }

    private void initialize() {

        ivCoin = (ImageView) findViewById(R.id.ivCoin);
        btnFlip = (Button) findViewById(R.id.btnFlip);
        tvResult = (TextView) findViewById(R.id.tvResult);

    }

    private void clickEvent() {

        btnFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCoin();
            }
        });

    }

    private void flipCoin() {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1000);
        fadeOut.setFillAfter(true);
        tvResult.setText(R.string.empty);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                int i = random.nextInt(100);

                Animation fadeIn = new AlphaAnimation(0, 5);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(10000);
                fadeIn.setFillAfter(true);

                ivCoin.startAnimation(fadeIn);

                if (i % 2 == 0) {
                    ivCoin.setImageResource(R.drawable.tails);
                    tvResult.setText(R.string.tails);
                } else {
                    ivCoin.setImageResource(R.drawable.heads);
                    tvResult.setText(R.string.heads);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        ivCoin.startAnimation(fadeOut);
    }
}
