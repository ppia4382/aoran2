package com.andres.aoran;


import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.andres.aoran2.R;

import static com.andres.aoran.QuizBook.questionSound;


public class QuizActivity extends AppCompatActivity {

    private TextView mScoreView;
    private ImageView mImageView;
    private int mQuestionSound;
    private Button mTrueButton;
    private Button mFalseButton;
    private TextView mQuestion;

    private boolean mAnswer;
    private int mScore = 0;
    private int mQuestionNumber =0;

    private SoundPool soundPool;
    private int correct_se;
    private int tryagain_se;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool(12, AudioManager.STREAM_MUSIC, 0);
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
        correct_se = soundPool.load(this, R.raw._yougotit_copyright2021_patrician_andres, 1);
        tryagain_se = soundPool.load(this, R.raw.oopstryagain_copyright2021patrician_andres, 1);

        mImageView =(ImageView)findViewById(R.id.imageView);
        mQuestion =(TextView)findViewById(R.id.question);
        mScoreView =(TextView)findViewById(R.id.points);
        mTrueButton =(Button)findViewById(R.id.trueButton);
        mFalseButton =(Button)findViewById(R.id.falseButton);

        updateQuestion();

        //④ Logic for true button
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswer == true) {
                    soundPool.play(correct_se,1F,1F,0,0,1F);
                    mScore++;
                    updateScore(mScore);

                    if (mQuestionNumber == QuizBook.questions.length) {
                        Intent i = new Intent(QuizActivity.this, ResultsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", mScore);
                        i.putExtras(bundle);
                        QuizActivity.this.finish();
                        startActivity(i);
                    } else {
                        updateQuestion();
                    }
                } else {
                    soundPool.play(tryagain_se,1F,1F,0,0,1F);
                    if (mQuestionNumber == QuizBook.questions.length) {
                        Intent i = new Intent(QuizActivity.this, ResultsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", mScore);
                        i.putExtras(bundle);
                        QuizActivity.this.finish();
                        startActivity(i);
                    } else {
                        updateQuestion();
                    }
                }
            }
        });

        //⑤ Logic for false button
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswer == false) {
                    soundPool.play(correct_se,1F,1F,0,0,1F);
                    mScore++;
                    updateScore(mScore);

                    if (mQuestionNumber == QuizBook.questions.length) {
                        Intent i = new Intent(QuizActivity.this, ResultsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", mScore);
                        i.putExtras(bundle);
                        QuizActivity.this.finish();
                        startActivity(i);
                    } else {
                        updateQuestion();
                    }
                } else {
                    soundPool.play(tryagain_se,1F,1F,0,0,1F);
                    if (mQuestionNumber == QuizBook.questions.length) {
                        Intent i = new Intent(QuizActivity.this, ResultsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", mScore);
                        i.putExtras(bundle);
                        QuizActivity.this.finish();
                        startActivity(i);
                    } else {
                        updateQuestion();
                    }
                }
            }
        });

    }
    private void updateQuestion () {
        mImageView.setImageResource(QuizBook.images[mQuestionNumber]);
        mQuestion.setText(QuizBook.questions[mQuestionNumber]);
        mAnswer = QuizBook.answers[mQuestionNumber];
        mAnswer = QuizBook.answers[mQuestionNumber];
        mQuestionNumber++;

         /*②create a method to update the score. it takes 1 parameter in a form of an integer. This
         method takes the score variable which is an integer and converts it into a string by
         concatinating it with an empty string and then sets the text to mscoreview  equal to it.
         */
    }
    private void updateScore ( int mScore){
       mScoreView.setText("" + this.mScore);
     }
            /* ③ logic on true button is now to be made but before that,
               we need to create a button for results activity and an
               activity on the score results.xml to handle how the quiz ends.

               We will first need an OnClickListener and in it, we'll start with an if statement.
               once clicked, the score variable will be updated, we'll then call update score to add a score to
               mview.We need to perform a check afterwards to see if we are on the last question of the quiz.
             */

}
