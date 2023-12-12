package com.example.myswtlc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.myswtlc.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void openTest(View view){
        Intent intent = new Intent(this, TestButton.class);
        EditText editText = findViewById(R.id.edit_name);
        String name = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, name);
        startActivity(intent);
    }

    public void convTemp(View view){
        Intent intent = new Intent(this, TempConverter.class);
        startActivity(intent);
    }

    public void openUtils(View view){
        Intent intent = new Intent(this, OfficeUtils.class);
        startActivity(intent);
    }

    public void openCl(View view){
        Intent intent = new Intent(this, SimpleContactListActivity.class);
        startActivity(intent);
    }

    public void openSens(View view){
        Intent intent = new Intent(this, SensorData.class);
        startActivity(intent);
    }

    public void openStor(View view){
        Intent intent = new Intent(this, InternalStorage.class);
        startActivity(intent);
    }

    public void openImage(View view){
        Intent intent = new Intent(this, ImageDownloaderActivity.class);
        startActivity(intent);
    }
}