package com.exercice.autosender3;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
//sqlite tabelle abruffen und inhalt in list speichern und in dem listview zeigen

public class Date extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private DatePickerDialog.OnDateSetListener listener;

    public static Date newInstance(DatePickerDialog.OnDateSetListener listener) {
        Date fragment = new Date();
        fragment.setListener(listener);
        return fragment;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //final View view = getActivity().getLayoutInflater().inflate(R.layout.date_picker_fragment, null);

        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        if (listener != null) {
            Calendar c = Calendar.getInstance();
            c.set(year, month, day);
            listener.onDateSet(view, year, month, day);
        }
    }


}