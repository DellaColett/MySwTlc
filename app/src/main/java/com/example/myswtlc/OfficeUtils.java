package com.example.myswtlc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class OfficeUtils extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_utils);
    }

    public void browse(View view){
        EditText url_text = findViewById(R.id.url);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+url_text.getText().toString()));
        startActivity(intent);
    }

    public void phone(View view){
        EditText phone_text = findViewById(R.id.phone_number);
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone_text.getText().toString()));
        startActivity(intent);
    }

    public void mail(View view){
        EditText email_to_text = findViewById(R.id.email_to);
        EditText email_subject_text = findViewById(R.id.email_subject);
        EditText email_edittext = findViewById(R.id.email_text);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email_to_text.getText().toString()});
        intent.putExtra(Intent.EXTRA_SUBJECT, email_subject_text.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, email_edittext.getText().toString());
        startActivity(intent);
    }

    public void position(View view){
        EditText position_text= findViewById(R.id.location);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        String position_string=position_text.getText().toString();
        position_string.replaceAll(" ", "+");
        intent.setData(Uri.parse("geo:0,0?q=" + position_string));
        startActivity(intent);
    }

}