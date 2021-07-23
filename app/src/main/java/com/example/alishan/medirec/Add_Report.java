package com.example.alishan.medirec;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class Add_Report extends AppCompatActivity {
    private Button mButton;
    final Context c = this;
    int mHour;
    int mMinute,mYear,mMonth,mDay;
      String time;
      String date ,currtime ,currdate ;
    int p_id;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_report);
        p_id= getIntent().getIntExtra("p_id",1);
        mButton = (Button) findViewById(R.id.btnSave);
        final TextInputLayout Systolic = (TextInputLayout) findViewById(R.id.input_layout_systolic);
        final TextInputLayout diastolic = (TextInputLayout)findViewById(R.id.input_layout_diatolic);
        final TextInputLayout Temperature = (TextInputLayout)findViewById(R.id.input_layout_Temp);
        final  TextInputLayout Respiration = (TextInputLayout)findViewById(R.id.input_layout_repiration);
        final TextInputLayout Sugar =(TextInputLayout) findViewById(R.id.input_layout_Bloodglu);
        final TextInputLayout SleepHours = (TextInputLayout)findViewById(R.id.input_layout_sleep) ;
        final TextInputLayout pluse = (TextInputLayout)findViewById(R.id.input_layout_pluse) ;
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
                final View mView = layoutInflaterAndroid.inflate(R.layout.datetimedialog, null);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
                alertDialogBuilderUserInput.setView(mView);
                alertDialogBuilderUserInput.setTitle("CheckUp Date and Time");
              // showTime =(TextView)mView.findViewById(R.id.showTime) ;
                Button timeBtn=(Button)mView.findViewById(R.id.timeBtn);
                Button dateBtn = (Button)mView.findViewById(R.id.dateBtn);
                currtime=getCurrentTime(mView);
                currdate=getCurrentDate(mView);
               final TextView tim = (TextView)mView.findViewById(R.id.showTime);
                tim.setText(getCurrentTime(mView));
                final TextView dat = (TextView)mView.findViewById(R.id.showdate);
                dat.setText(getCurrentDate(mView));
                timeBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {


                         currtime=tiemPicker();
                        tim.setText(currtime);


                    }
                });
                dateBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {


                         currdate = datePicker();
                        dat.setText(currdate);


                    }
                });
                alertDialogBuilderUserInput
                        .setCancelable(false)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                // ToDo get user input here
                                Report_Model Rm =new Report_Model();
                                Rm.setP_id(p_id);
                                Rm.setTime(currtime);
                                Rm.setDate(currdate);
                                Rm.setBloodPressure(Systolic.getEditText().getText().toString()+"/"+diastolic.getEditText().getText().toString());
                                Rm.setTemperature(Temperature.getEditText().getText().toString());
                                Rm.setRespiration(Respiration.getEditText().getText().toString());
                                Rm.setSleepHours(SleepHours.getEditText().getText().toString());
                                Rm.setSugar(Sugar.getEditText().getText().toString());
                                Rm.setPluse(pluse.getEditText().getText().toString());
                                DBHelper db=new DBHelper(getApplicationContext());
                                db.insertcheckup(Rm);
                                ArrayList<Report_Model> arr=db.getRecordByPatientId(1);
                                Toast.makeText(getApplicationContext()," Checkup Added  Sucessfully ",Toast.LENGTH_LONG).show();

                            }
                        })

                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                });

                AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                alertDialogAndroid.show();
            }
        });

    }
    private String tiemPicker(){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,int minute) {

                        mHour = hourOfDay;
                        mMinute = minute;
                         time =" "+hourOfDay + ":" + minute;
                        //et_show_date_time.setText(date_time+" "+hourOfDay + ":" + minute);


                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
        return time;
    }
    private String  datePicker(){

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                        //*************Call Time Picker Here ********************
                       // tiemPicker();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
        return date;
    }
    public String getCurrentTime(View view) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate =   mdformat.format(calendar.getTime());
        return strDate;
    }
    public String getCurrentDate(View view)
    {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        date = mDay + "-" + mMonth + "-" + mYear;
        return date;
    }

}

