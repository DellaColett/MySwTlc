package com.example.myswtlc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Contacts extends AppCompatActivity {

    public final static String MyNEW = "MyNew";
    public static final String Contact = "contactKey";
    public final static String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String Phone = "phoneKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        SharedPreferences pref = getSharedPreferences(MyNEW, MODE_PRIVATE);

        TextView textView = findViewById(R.id.first_contact);

        String c = pref.getString(Contact, "Contact");
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(c, textView.getText().toString());
        editor.apply();

        textView.append(c);

        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

        String n = sharedPreferences.getString(Name, "Name");
        String ph = sharedPreferences.getString(Phone, "Phone");
        String e = sharedPreferences.getString(Email, "Email");

        textView.append(n + "\n" + ph + "\n" + e + "\n\n");

        editor = pref.edit();
        editor.putString(Contact, textView.getText().toString());
        editor.apply();
        Toast.makeText(Contacts.this, "To Contact List", Toast.LENGTH_LONG).show();
    }

}