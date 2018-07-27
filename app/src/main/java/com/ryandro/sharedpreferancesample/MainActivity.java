package com.ryandro.sharedpreferancesample;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_login, et_password;
    private String KEY_ID="KEY_ID", PASSWORD_KEY="PASSWORD_KEY", DEFAULT = "N/A";
//    private String KEY_ID, PASSWORD_KEY, DEFAULT = "N/A";

    private TextView tv_Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_login = (EditText) findViewById(R.id.et_login);
        et_password = (EditText) findViewById(R.id.et_password);
        tv_Data = (TextView) findViewById(R.id.tv_Data);
        tv_Data.setVisibility(View.INVISIBLE);

    }

    public void SaveData(View view) {
        String id = et_login.getText().toString();
        String pass = et_password.getText().toString();

        if (id != null && !(id.equalsIgnoreCase("")) && pass != null && !(pass.equalsIgnoreCase(""))) {
            SharedPreferences preferences = getSharedPreferences("MyDataFile", Context.MODE_PRIVATE);
//            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putString(KEY_ID, id);//1000
            edit.commit();
            edit.putString(PASSWORD_KEY, pass);//1000
            edit.commit();

            Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(MainActivity.this, "Please enter ID Password", Toast.LENGTH_SHORT).show();
    }

    public void DisplayData(View view) {
        SharedPreferences preferences = getSharedPreferences("MyDataFile", Context.MODE_PRIVATE);
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String id = preferences.getString(KEY_ID, DEFAULT);
        String pass = preferences.getString(PASSWORD_KEY, DEFAULT);

        if (!id.equalsIgnoreCase(DEFAULT) && !pass.equalsIgnoreCase(DEFAULT)) {
            tv_Data.setText("ID : " + id + " \n Password : " + pass);
            tv_Data.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
            tv_Data.setVisibility(View.INVISIBLE);
        }
    }
}
