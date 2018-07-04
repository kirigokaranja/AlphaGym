package com.kirigokaranja.alphagym;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.kirigokaranja.alphagym.Classes.DOBDateFragment;
import java.util.Calendar;

public class LogWorkout extends AppCompatActivity {
    TextInputLayout Name;
    TextView Date;
    ImageView workoutImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_workout);

        Calendar cal = Calendar.getInstance();
        processDatePickerResult(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

    Name = (TextInputLayout)findViewById(R.id.workout_name);
        workoutImg = (ImageView)findViewById(R.id.workout_img);


    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);

        String dateMessage = (month_string + "/" + day_string + "/" + year_string);

        Date = (TextView) findViewById(R.id.wdate);
        Date.setText(dateMessage);



    }

    public void showdate(View view) {
        DOBDateFragment fragment = new DOBDateFragment();
        fragment.show(getSupportFragmentManager(), getString(R.string.date_picker));
    }
}
