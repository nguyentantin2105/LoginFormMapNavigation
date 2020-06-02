package com.example.formlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {
    MaterialEditText email, password;
    Button signin;
    CheckBox rememberSignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPass);
        rememberSignin = findViewById(R.id.ckbRemember);
        signin = findViewById(R.id.btnLog);

    }
}