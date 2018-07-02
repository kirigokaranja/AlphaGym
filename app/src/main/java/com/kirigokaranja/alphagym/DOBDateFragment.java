package com.kirigokaranja.alphagym;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DOBDateFragment extends DialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

      //  return new DatePickerDialog(getActivity(),  R.style.datepicker, this, year, month, day);

        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(),  year, month, day);


    }

//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int day) {
//
//        MoreInformation activity = (MoreInformation) getActivity();
//
//        activity.processDatePickerResult(year, month, day);
//    }
}
