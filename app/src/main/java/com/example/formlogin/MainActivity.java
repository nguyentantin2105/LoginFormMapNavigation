package com.example.formlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    MaterialEditText email, password;
    Button signin;
    CheckBox rememberSignin;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPass);
        rememberSignin = findViewById(R.id.ckbRemember);
        signin = findViewById(R.id.btnLog);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();
                if (TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)){
                    Toast.makeText(MainActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                }
                else{
                    login(txtEmail, txtPassword);
                }
            }
        });

        String RememberStatus = sharedPreferences.getString(getResources().getString(R.string.perfLoginState),"");

        if (RememberStatus.equals("Signed in")){
            startActivity(new Intent(MainActivity.this, AppStartActivity.class));
        }
    }

    private void login(final String email, final String password){
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Registering New Account");
        progressDialog.show();
        String url = "http://10.0.0.1/registerform/login.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Login success")) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (rememberSignin.isChecked()) {
                        editor.putString(getResources().getString(R.string.perfLoginState), "Signed in");
                    } else {
                        editor.putString(getResources().getString(R.string.perfLoginState), "Signed out");
                    }
                    startActivity(new Intent(MainActivity.this, AppStartActivity.class));
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this,response,Toast.LENGTH_SHORT).show();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("email",email);
                param.put("pass", password);
                return param;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleTon.getInstance(MainActivity.this).addToRequestQueue(request);
    }

}