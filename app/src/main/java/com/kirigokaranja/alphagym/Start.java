package com.kirigokaranja.alphagym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final ProgressBar pbar = (ProgressBar)findViewById(R.id.progressBar);

        final Intent intent = new Intent(this, Login.class);

        Thread timer = new Thread() {

            public void run () {

                try {
                    sleep(3000);
                    pbar.setProgress(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();

                } finally {

                    startActivity(intent);
                    finish();
                }
            }
        };
            timer.start();
        }



}
