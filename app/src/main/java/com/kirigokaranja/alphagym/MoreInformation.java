package com.kirigokaranja.alphagym;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kirigokaranja.alphagym.Classes.DOBDateFragment;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MoreInformation extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_information);


    }

    public void nextactivity(View view) {
        startActivity(new Intent(this,Home.class));
    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DOBDateFragment();
        newFragment.show(getSupportFragmentManager(),
                getString(R.string.date_picker));
    }

    public void setDate(final Calendar calendar){
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        ((TextView) findViewById(R.id.slected_dob)).setText(dateFormat.format(calendar.getTime()));
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);

        String dateMessage = (month_string + "/" + day_string + "/" + year_string);

        Toast.makeText(this, "Date: " + dateMessage, Toast.LENGTH_SHORT).show();


    }

    public void changeGender(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){

            case R.id.radiomale:
                if (checked){

                }
                break;
            case R.id.radiofemale:
                if (checked){

                }
                break;

        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar calendar = new GregorianCalendar(year, month, day);
        setDate(calendar);
    }
}
