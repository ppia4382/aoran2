package com.andres.aoran;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.andres.aoran2.R;

public class ResultsActivity extends AppCompatActivity {

    TextView mGrade, mFinalScore;
    Button mRetryButton, mQuitButton;
    private SoundPool soundPool;
    private int tada_soundbiblecom;
    private int letsplayagain_copyright2021patrician_andres;
    private int goodbye_copyright2021patrician_andres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        } else {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(12)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        tada_soundbiblecom = soundPool.load(this, R.raw.tada_soundbiblecom, 1);
        letsplayagain_copyright2021patrician_andres = soundPool.load(this, R.raw.letsplayagain_copyright2021patrician_andres, 1);
        goodbye_copyright2021patrician_andres = soundPool.load(this, R.raw.goodbye_copyright2021patrician_andres, 1);

        mGrade = findViewById(R.id.grade);
        mFinalScore = findViewById(R.id.outOf);
        mRetryButton = findViewById(R.id.retry);
        mQuitButton = findViewById(R.id.quit);

        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("finalScore");
        soundPool.play(tada_soundbiblecom,1F,1F,0,0,1F);
        mFinalScore.setText("You scored " + score + " out of " + QuizBook.questions.length);

                if (score == 10){
                    mGrade.setText("AMAZING!");
                }else if (score == 8){
                    mGrade.setText("GOOD JOB!");
                }else if (score == 7) {
                    mGrade.setText("VERY GOOD!");
                }else {
                    mGrade.setText("NICE TRY!");
                }

                mRetryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        soundPool.play(letsplayagain_copyright2021patrician_andres,1F,1F,0,0,1F);
                        startActivity(new Intent(ResultsActivity.this, QuizActivity.class));
                        ResultsActivity.this.finish();
                    }
                });

                mQuitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    soundPool.play(goodbye_copyright2021patrician_andres,1F,1F,0,0,1F);
                    finish();
                    }
                });
    }
}


