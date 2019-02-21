package nl.pieterandriesberg.eggtimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewTime;
    SeekBar seekBarSetTime;
    Boolean counterIsActive = false;
    Button buttonGO;
    CountDownTimer countDownTimer;

    public void resetTimer(){

        textViewTime.setText("0:30");
        seekBarSetTime.setProgress(30);
        seekBarSetTime.setEnabled(true);
        countDownTimer.cancel();
        buttonGO.setText("GO!");
        counterIsActive = false;
    }

    public void buttonClicked(View view) {


        if (counterIsActive) {

          resetTimer();

        } else {


            counterIsActive = true;
            seekBarSetTime.setEnabled(false);

            buttonGO.setText("STOP!");


            countDownTimer = new CountDownTimer(seekBarSetTime.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {

                    //play airhorn
                    MediaPlayer mediaPlayer = new MediaPlayer().create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }
    }

            public void updateTimer ( int secondsLeft){

                int minutes = secondsLeft / 60;
                int seconds = secondsLeft - (minutes * 60);

                String secondString = Integer.toString(seconds);

                if (seconds <= 9) {
                    secondString = "0" + secondString;
                }

                textViewTime.setText(Integer.toString(minutes) + ":" + secondString);
            }

            @Override
            protected void onCreate (Bundle savedInstanceState){
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                seekBarSetTime = findViewById(R.id.seekBarSetTime);
                textViewTime = findViewById(R.id.textViewTime);
                buttonGO = findViewById(R.id.buttonGo);

                int max = 600;
                int startingPostion = 30;
                seekBarSetTime.setMax(max);
                seekBarSetTime.setProgress(startingPostion);

                seekBarSetTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        updateTimer(progress);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

            }
        }

