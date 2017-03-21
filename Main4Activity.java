package com.example.oh.practice2;

import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TimePicker;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener {
    EditText Edit1, Edit2, Edit3;
    TextView Re_Date, Re_Time, Adult, Teen, Child;
    Switch Swit;
    Chronometer Chro;
    DatePicker Date;
    TimePicker Time;
    Button Pre, Next;
    GridLayout Grid;
    TableLayout Table;
    int Index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Edit1 = (EditText)findViewById(R.id.editText);
        Edit2 = (EditText)findViewById(R.id.editText4);
        Edit3 = (EditText)findViewById(R.id.editText5);

        Date = (DatePicker)findViewById(R.id.datePicker);

        Time = (TimePicker)findViewById(R.id.timePicker);

        Grid = (GridLayout)findViewById(R.id.grid);

        Table = (TableLayout)findViewById(R.id.table);

        Re_Date = (TextView)findViewById(R.id.textView38);
        Re_Time = (TextView)findViewById(R.id.textView36);
        Adult = (TextView)findViewById(R.id.textView34);
        Teen = (TextView)findViewById(R.id.textView32);
        Child = (TextView)findViewById(R.id.textView30);

        Swit = (Switch)findViewById(R.id.switch1);
        Chro= (Chronometer)findViewById(R.id.chronometer1);

        Pre = (Button)findViewById(R.id.button1);
        Next = (Button)findViewById(R.id.button2);

        Pre.setEnabled(false);
        Next.setEnabled(false);
        Pre.setOnClickListener(this);
        Next.setOnClickListener(this);

        Chro.setBase(SystemClock.elapsedRealtime());

        Swit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Index++;
                    ChangePage();
                    Chro.setBase(SystemClock.elapsedRealtime());
                    Chro.start();
                }
                else {
                    Index = 0;
                    ChangePage();
                    Chro.stop();
                }
            }
        });
    }

    void setDate(){
        Re_Date.setText(Date.getYear() + "년 " + (Date.getMonth()+1) + "월 " + Date.getDayOfMonth() + "일");
    }

    void setTime(){
        int Hour, Min;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Hour = Time.getHour();
            Min = Time.getMinute();
        }
        else {
            Hour = Time.getCurrentHour();
            Min = Time.getCurrentMinute();
        }
        Re_Time.setText(String.format("%d시 %d분", Hour, Min));
    }

    void setReset(){
        Edit1.setText(null);
        Edit2.setText(null);
        Edit3.setText(null);
    }
    void setPeople(){
        String adult = Edit1.getText().toString();
        String teen = Edit2.getText().toString();
        String child = Edit3.getText().toString();

        if (adult.length() == 0){
            Edit1.setText("0");
            adult = Edit1.getText().toString();
        }
        if (teen.length() == 0){
            Edit2.setText("0");
            teen = Edit2.getText().toString();
        }
        if (child.length() == 0){
            Edit3.setText("0");
            child = Edit3.getText().toString();
        }

        Adult.setText(adult + "명");
        Teen.setText(teen + "명");
        Child.setText(child + "명");

        setReset();
    }


    void ChangePage() {
        if (Index == 0){
            Date.setVisibility(View.INVISIBLE);
            Time.setVisibility(View.INVISIBLE);
            Table.setVisibility(View.INVISIBLE);
            Grid.setVisibility(View.INVISIBLE);
        }
        else if (Index == 1) {
            Pre.setEnabled(false);
            Next.setEnabled(true);
            Date.setVisibility(View.VISIBLE);
            Time.setVisibility(View.INVISIBLE);
            Table.setVisibility(View.INVISIBLE);
            Grid.setVisibility(View.INVISIBLE);
        }
        else if (Index == 2) {
            Pre.setEnabled(true);
            setDate();
            Date.setVisibility(View.INVISIBLE);
            Time.setVisibility(View.VISIBLE);
            Table.setVisibility(View.INVISIBLE);
            Grid.setVisibility(View.INVISIBLE);
        }
        else if (Index == 3) {
            Next.setEnabled(true);
            setTime();
            Date.setVisibility(View.INVISIBLE);
            Time.setVisibility(View.INVISIBLE);
            Table.setVisibility(View.VISIBLE);
            Grid.setVisibility(View.INVISIBLE);
        }
        else if (Index == 4) {
            Next.setEnabled(false);
            setPeople();
            Date.setVisibility(View.INVISIBLE);
            Time.setVisibility(View.INVISIBLE);
            Table.setVisibility(View.INVISIBLE);
            Grid.setVisibility(View.VISIBLE);
        }
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1 :
                if (Index > 1)
                    Index--;
                ChangePage();
                break;
            case R.id.button2 :
                if (Index < 4)
                    Index++;
                ChangePage();
                break;
            default :
                break;
        }
    }
}
