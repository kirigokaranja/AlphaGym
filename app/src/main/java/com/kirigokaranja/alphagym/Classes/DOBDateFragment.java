package com.kirigokaranja.alphagym.Classes;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;

import com.kirigokaranja.alphagym.LogWorkout;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DOBDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),this, year, month, day);

      //  return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(),  year, month, day);


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {

        LogWorkout activity = (LogWorkout) getActivity();

        activity.processDatePickerResult(year, month, day);
    }
}
