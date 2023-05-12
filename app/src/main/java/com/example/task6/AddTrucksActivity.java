package com.example.task6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.task6.data.Trucks;

import java.util.Calendar;
import java.util.Locale;

public class AddTrucksActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;

    EditText name;
    Button add,dateButton,timeButton;
    int hour,min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trucks);

        name = findViewById(R.id.editTextName);
        initDatePicker();
        dateButton = findViewById(R.id.date);
        timeButton = findViewById(R.id.selectTime);

        add = findViewById(R.id.button);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if(TextUtils.isEmpty(name.getText())){
                    setResult(RESULT_CANCELED, intent);
                }
                else {

                    Trucks trucks = new Trucks(0,name.getText().toString(),
                            dateButton.getText().toString());

                    String nameValue = name.getText().toString();
                    String descValue = dateButton.getText().toString();
                    intent.putExtra("add_value1", nameValue);
                    intent.putExtra("add_value2", descValue);
                    setResult(RESULT_OK, intent);
                }

                finish();
            }
        });
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);

            }
        };
        Calendar cal =Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;

        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);


    }
    private String makeDateString(int day, int month, int year)
    {
        return month+ " " + day + " " +year;
    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay;
                min = minute;
                timeButton.setText(String.format(Locale.getDefault(),
                        "%02d:%02d",hour,min));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,onTimeSetListener,hour,min,true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    public void openDatePicker(View view) {

        datePickerDialog.show();
    }
}