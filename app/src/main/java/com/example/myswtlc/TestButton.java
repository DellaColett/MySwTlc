package com.example.myswtlc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class TestButton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_test);
        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.salutation_view);
        String salutation = "Hello, "+name;
        textView.setText(salutation);
    }
    public void sendMessage(View view){
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        TextView textView = (TextView) findViewById(R.id.text_message);
        RadioButton Blue = findViewById(R.id.radio_blue);
        RadioButton Red = findViewById(R.id.radio_red);
        if (Blue.isChecked()){
            textView.setTextColor(Color.BLUE);
        }
        else if (Red.isChecked()){
            textView.setTextColor(Color.RED);
        }
        else{
            textView.setTextColor(Color.GREEN);
        }
        textView.append(message+"\n");
        editText.setText(null);
    }
}