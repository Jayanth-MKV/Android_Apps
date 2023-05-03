package com.scratchcard.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.anupkumarpanwar.scratchview.ScratchView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_scratch_card_layout);


        ScratchView scratchView = findViewById(R.id.scratch_view);
        scratchView.setRevealListener(new ScratchView.IRevealListener() {
            @Override
            public void onRevealed(ScratchView scratchView) {
                Toast.makeText(getApplicationContext(), "Reveled", Toast.LENGTH_LONG).show();;
            }

            @Override
            public void onRevealPercentChangedListener(ScratchView scratchView, float percent) {
                if (percent>=0.3) {

                    scratchView.reveal();
                    playMusic();

                    LottieAnimationView lottieAnimationView=findViewById(R.id.ribbon);
                    LottieAnimationView ExplodingRibbon=findViewById(R.id.ribbon_exploding);
                    ExplodingRibbon.setVisibility(View.VISIBLE);
                    lottieAnimationView.setVisibility(View.VISIBLE);
                    ExplodingRibbon.setSpeed(1.5f);
                    lottieAnimationView.setSpeed(2);
                    ExplodingRibbon.playAnimation();
                    lottieAnimationView.playAnimation();



                    Log.d("Reveal Percentage", "onRevealPercentChangedListener: " + String.valueOf(percent));
                }
            }
        });
    }

    private void playMusic() {


        MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.reveal);
        ring.start();

    }
}
