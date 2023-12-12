package com.example.myswtlc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SimpleContactListActivity extends AppCompatActivity {

    EditText ed1, ed2, ed3;
    Button b1, b2;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String Phone = "phoneKey";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_contact_list);

        ed1 = findViewById(R.id.cl_name_edittext);
        ed2 = findViewById(R.id.cl_phone_edittext);
        ed3 = findViewById(R.id.cl_mail_edittext);
        b1 = findViewById(R.id.cl_button);
        b2 = findViewById(R.id.cl_pass_button);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

        String n = sharedPreferences.getString(Name, "Name");
        String ph = sharedPreferences.getString(Phone, "Phone");
        String e = sharedPreferences.getString(Email, "Email");

        ed1.setText(n);
        ed2.setText(ph);
        ed3.setText(e);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = ed1.getText().toString();
                String ph = ed2.getText().toString();
                String e = ed3.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(Name, n);
                editor.putString(Phone, ph);
                editor.putString(Email, e);
                editor.apply();
                Toast.makeText(SimpleContactListActivity.this,"Thanks",Toast.LENGTH_LONG).show();
            }
        });

    }
     public void openContact(View view){
         Intent intent = new Intent(this, Contacts.class);
         startActivity(intent);
     }
}