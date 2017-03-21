package com.example.oh.practice2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import static android.view.View.VISIBLE;

public class Main3Activity extends AppCompatActivity {
    EditText e1, e2, e3;
    Button b1, b2;
    TextView t1, t2;
    ImageView v1;
    InputMethodManager io;
    boolean check = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        init();
    }

    void Max(int a,int b,int c){
        if (a > 100) {
            e1.requestFocus();
            check = false;
        }
        else if (b > 100) {
            e2.requestFocus();
            check = false;
        }
        else if (c > 100) {
            e3.requestFocus();
            check = false;
        }
    }

    void CheckNum(int num) {
        if (num >= 90)
            v1.setImageResource(R.drawable.a);
        else if (num >= 80)
            v1.setImageResource(R.drawable.b);
        else if (num >= 70)
            v1.setImageResource(R.drawable.c);
        else if (num >= 60)
            v1.setImageResource(R.drawable.d);
        else
            v1.setImageResource(R.drawable.f);
    }

    void init(){
        io = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        e1 = (EditText)findViewById(R.id.editText1);
        e2 = (EditText)findViewById(R.id.editText2);
        e3 = (EditText)findViewById(R.id.editText3);

        b1 = (Button)findViewById(R.id.button10);
        b2 = (Button)findViewById(R.id.button11);

        t1 = (TextView)findViewById(R.id.textView6);
        t2 = (TextView)findViewById(R.id.textView8);

        v1 = (ImageView)findViewById(R.id.imageView1);

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Kor = e1.getText().toString();
                String Math = e2.getText().toString();
                String Eng = e3.getText().toString();

                if (Kor.length() == 0){
                    e1.setText("0");
                    Kor = e1.getText().toString();
                }

                if (Math.length() == 0){
                    e2.setText("0");
                    Math = e2.getText().toString();
                }

                if (Eng.length() == 0) {
                    e3.setText("0");
                    Eng = e3.getText().toString();
                }

                Max(Integer.parseInt(Kor),Integer.parseInt(Math),Integer.parseInt(Eng));

                if (check == false) {
                    Toast.makeText(getApplicationContext(), "다시 입력하시오", Toast.LENGTH_SHORT).show();
                    e1.setText(null);
                    e2.setText(null);
                    e3.setText(null);
                    check = true;
                }
                else {
                    int result1 = Integer.parseInt(Kor) + Integer.parseInt(Math) + Integer.parseInt(Eng);
                    int result2 = result1 / 3;

                    t1.setText(result1 + "점");
                    t2.setText(result2 + "점");

                    CheckNum(result2);

                    v1.setVisibility(VISIBLE);
                    io.hideSoftInputFromWindow(e3.getWindowToken(), 0);
                    check = true;
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e1.setText(null);
                e2.setText(null);
                e3.setText(null);
                t1.setText("0점");
                t2.setText("0점");
                v1.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "초기화 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
