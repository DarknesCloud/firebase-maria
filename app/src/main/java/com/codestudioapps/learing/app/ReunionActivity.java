package com.codestudioapps.learing.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Calendar;
public class ReunionActivity extends AppCompatActivity {
    private LinearLayout containerLayout;
    private LinearLayout formLayout;
    private Button addButton;
    private Button eventDateButton;
    private Button eventTimeButton;
    private int year, month, day, hour, minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion);

        containerLayout = findViewById(R.id.container_layout);
        formLayout = findViewById(R.id.form_layout);
        addButton = findViewById(R.id.add_button);
        eventDateButton = findViewById(R.id.event_date_button);
        eventTimeButton = findViewById(R.id.event_time_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFormVisibility();
            }
        });

        eventDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        eventTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        Button acceptButton = findViewById(R.id.accept_button);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateCardView();
                toggleFormVisibility();
            }
        });

    }

    private void toggleFormVisibility() {
        if (formLayout.getVisibility() == View.VISIBLE) {
            formLayout.setVisibility(View.GONE);
        } else {
            formLayout.setVisibility(View.VISIBLE);
        }
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        ReunionActivity.this.year = year;
                        month = monthOfYear;
                        day = dayOfMonth;
                        String date = day + "/" + (month + 1) + "/" + year;
                        eventDateButton.setText(date);
                    }
                },
                year, month, day);
        datePickerDialog.show();
    }

    private void showTimePicker() {
        final Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfDay) {
                        hour = hourOfDay;
                        minute = minuteOfDay;
                        String time = String.format("%02d:%02d", hour, minute);
                        eventTimeButton.setText(time);
                    }
                },
                hour, minute, true);
        timePickerDialog.show();
    }

    private void generateCardView() {
        EditText nameEditText = findViewById(R.id.event_name_edittext);
        EditText instructorEditText = findViewById(R.id.event_instructor_edittext);

        String name = nameEditText.getText().toString();
        String date = eventDateButton.getText().toString();
        String time = eventTimeButton.getText().toString();
        String instructor = instructorEditText.getText().toString();

        CardView cardView = new CardView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        int margin = getResources().getDimensionPixelSize(R.dimen.cardview_margin);
        layoutParams.setMargins(margin, margin, margin, margin);
        cardView.setLayoutParams(layoutParams);

        View cardViewContent = getLayoutInflater().inflate(R.layout.my_cardview, null);

        TextView nameTextView = cardViewContent.findViewById(R.id.event_title_textview);
        TextView dateTextView = cardViewContent.findViewById(R.id.event_date_textview);
        TextView instructorTextView = cardViewContent.findViewById(R.id.event_instructor_textview);

        nameTextView.setText(name);
        dateTextView.setText(date + " " + time);
        instructorTextView.setText(instructor);

        cardView.addView(cardViewContent);

        containerLayout.addView(cardView);
    }

}
