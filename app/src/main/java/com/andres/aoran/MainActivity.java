package com.andres.aoran;


import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.andres.aoran2.R;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private SoundPool soundPool;
    private int begin_chimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        begin_chimes = soundPool.load(this, R.raw.begin_chimes, 1);


        button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }

    public void start(){
        Intent intent = new Intent(this, QuizActivity.class);
        soundPool.play(begin_chimes,1F,1F,0,0,1F);
        startActivity(intent);

    }

    public void begin_chimes(View view) {
    }
}

