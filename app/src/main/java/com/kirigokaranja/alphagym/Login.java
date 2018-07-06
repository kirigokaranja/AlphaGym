package com.kirigokaranja.alphagym;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kirigokaranja.alphagym.Model.User;
import com.kirigokaranja.alphagym.SharedPref.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private TextInputLayout Email;
    private TextInputLayout Password;
    private Button Login;
    private static final String URL_LOGIN = "https://alphagymapplication.herokuapp.com/api/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = (TextInputLayout)findViewById(R.id.loginemail);
        Password = (TextInputLayout)findViewById(R.id.loginpassword);
        Login = (Button)findViewById(R.id.btnlogin);

        if (SharedPrefManager.getInstance(this).areLoggedIn()) {
            finish();
        startActivity(new Intent(this, Home.class));
    }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  s();
                logins();
            }
        });
    }


    public void signup(View view) {
        startActivity(new Intent(this,RegisterActivity.class));
    }
    public void s() {
        startActivity(new Intent(this,Home.class));
    }
    public void logins(){


        final String FirstName = Email.getEditText().getText().toString();
        final String FirstPassword = Password.getEditText().getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);

                    if (obj.getBoolean("status")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        JSONObject users = obj.getJSONObject("user");

                        User user = new User(
                                users.getInt("id"),
                                users.getString("first_name"),
                                users.getString("last_name"),
                                users.getString("email"),
                                users.getString("password")
                        );


                        SharedPrefManager.getInstance(getApplicationContext()).Login(user);


                        finish();
                        startActivity(new Intent(getApplicationContext(), Home.class));
                    } else {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", FirstName);
                params.put("password", FirstPassword);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
