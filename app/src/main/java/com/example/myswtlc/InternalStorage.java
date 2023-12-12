package com.example.myswtlc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class InternalStorage extends AppCompatActivity {

    EditText textmsg, filename;
    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        textmsg = (EditText) findViewById(R.id.edit_file);
        filename = (EditText) findViewById(R.id.file_name);

        Spinner spinner = (Spinner) findViewById(R.id.text_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.textSize, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
    }

    public void WriteBtn(View view){
        try{
            FileOutputStream fileOut = openFileOutput(filename.getText().toString() + ".txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileOut);
            outputWriter.write(textmsg.getText().toString());
            outputWriter.close();

            Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ReadBtn(View view){
        try {
            FileInputStream fileIn = openFileInput(filename.getText().toString() + ".txt");
            InputStreamReader inputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;

            while((charRead = inputRead.read(inputBuffer)) > 0){
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            inputRead.close();
            if (s.equals("")){
                textmsg.setText("This file it's empty!");
            }
            else {
                textmsg.setText(s);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void EditBtn(View view){
        TextView textView = findViewById(R.id.storage_view);
        textView.setMovementMethod(new ScrollingMovementMethod());
        Spinner spinner = (Spinner) findViewById(R.id.text_spinner);
        Integer index = spinner.getSelectedItemPosition();
        try{
            FileInputStream fileIn = openFileInput(filename.getText().toString() + ".txt");
            InputStreamReader inputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;

            while ((charRead = inputRead.read(inputBuffer)) > 0){
                String readString = String.copyValueOf(inputBuffer,  0, charRead);
                s += readString;
            }
            inputRead.close();
            if (s.equals("")){
                textView.append("This file it's empty!\n");
            }
            else{
                textView.append(s + "\n");
            }
            textmsg.setText(null);
            switch(index){
                case 0:
                    textView.setTextSize(12);
                    break;
                case 1:
                    textView.setTextSize(14);
                    break;
                case 2:
                    textView.setTextSize(16);
                    break;
                case 3:
                    textView.setTextSize(18);
                    break;
                case 4:
                    textView.setTextSize(20);
                    break;
                case 5:
                    textView.setTextSize(22);
                    break;
                case 6:
                    textView.setTextSize(24);
                    break;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}