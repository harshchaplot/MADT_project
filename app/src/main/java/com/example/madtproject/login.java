package com.example.madtproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class login extends AppCompatActivity {
    TextInputEditText uname,password;
    Button login;
    DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        uname = findViewById(R.id.et_uname_login);
        password = findViewById(R.id.et_pwd_login);
        login = findViewById(R.id.login);
        dbManager = new DBManager(getApplicationContext());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.open();
                String pwd_db = "";
                String username = uname.getText().toString();
                try{
                    String query = "Select * From LOGIN where UNAME = '"+username+"'";
                    String pwd = password.getText().toString();
                    pwd_db = dbManager.getPassword(query);
                    pwd_db += " is the password";
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(),pwd_db,Toast.LENGTH_LONG).show();
                dbManager.close();
            }
        });
    }
}
