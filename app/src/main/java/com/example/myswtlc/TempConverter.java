package com.example.myswtlc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class TempConverter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_converter);
    }

    public void convT(View view){
        EditText editText = findViewById(R.id.enter_temp);
        RadioButton FarToCel = findViewById(R.id.far_to_cel);
        TextView textView = findViewById(R.id.conversion);
        float t = Float.parseFloat(editText.getText().toString());
        if (FarToCel.isChecked()){
            textView.append(t+"°F = ");
            t = (t-32)*9/5;
            textView.append(t+"°C\n");
        }
        else{
            textView.append(t+"°C = ");
            t = t*5/9+32;
            textView.append(t+"°F\n");
        }
        editText.setText(null);
    }
}