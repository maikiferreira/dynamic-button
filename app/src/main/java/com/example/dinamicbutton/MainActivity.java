package com.example.dinamicbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private LinearLayout layoutLinear;
    private Button btn1;
    private Button btn2;
    private boolean btnExists = false;
    private static final String [] DAYS_OF_THE_WEEK = {
            "Domingo",
            "Segunda-Feira",
            "Terça-Feira",
            "Quarta-Feira",
            "Quinta-Feira",
            "Sexta-Feira",
            "Sábado"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize(){
        layoutLinear = (LinearLayout) findViewById(R.id.layoutLinear);
        btn1 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createButton();
            }
        });
    }

    private void createButton(){
        if (btnExists) return;

        btnExists = true;
        btn2 = new Button(this);
        btn2.setText("BOTÂO 2");
        btn2.setBackgroundColor(Color.BLUE);
        btn2.setLayoutParams(btn1.getLayoutParams());

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeButton();
            }
        });

        btn2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showToast();
                return false;
            }
        });

        layoutLinear.addView(btn2);
    }

    private void removeButton(){
        layoutLinear.removeView(btn2);
        btnExists = false;
    }

    private void showToast(){
        Calendar cal = Calendar.getInstance();

        Toast.makeText(getApplicationContext(), String.format("Hoje é %s", DAYS_OF_THE_WEEK[cal.get(Calendar.DAY_OF_WEEK) - 1]), Toast.LENGTH_SHORT).show();
    }
}