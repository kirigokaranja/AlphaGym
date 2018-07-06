package com.kirigokaranja.alphagym;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.kirigokaranja.alphagym.Classes.InstructorDetails;
import com.kirigokaranja.alphagym.Model.User;
import com.kirigokaranja.alphagym.SharedPref.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout firstName;
    private TextInputLayout lastName;
    private TextInputLayout Email;
    private TextInputLayout Password;
    private TextInputLayout confirmPassword;
    private Button create;
    ProgressBar progressBar;
    private static final String URL_REGISTER = "https://alphagymapplication.herokuapp.com/api/register";

    public AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        progressBar = (ProgressBar) findViewById(R.id.loader);
        firstName = (TextInputLayout)findViewById(R.id.firstname);
        lastName = (TextInputLayout)findViewById(R.id.lastname);
        Email = (TextInputLayout)findViewById(R.id.email);
        Password = (TextInputLayout)findViewById(R.id.password);
        confirmPassword = (TextInputLayout)findViewById(R.id.confpassword);

        create = (Button)findViewById(R.id.btnregister);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();

                if (awesomeValidation.validate()) {
                    register();
                }
            }
        });

    }

    public void validation(){

        awesomeValidation.addValidation(this, R.id.firstname, RegexTemplate.NOT_EMPTY, R.string.empty_fileds);
        awesomeValidation.addValidation(this, R.id.lastname, RegexTemplate.NOT_EMPTY, R.string.empty_fileds);
        awesomeValidation.addValidation(this, R.id.email, RegexTemplate.NOT_EMPTY, R.string.empty_fileds);
        awesomeValidation.addValidation(this, R.id.password, RegexTemplate.NOT_EMPTY, R.string.empty_fileds);
        awesomeValidation.addValidation(this, R.id.confpassword, RegexTemplate.NOT_EMPTY, R.string.empty_fileds);

        awesomeValidation.addValidation(this, R.id.email, Patterns.EMAIL_ADDRESS, R.string.invalid_email);

        String regexPassword = ".{4,}";
        awesomeValidation.addValidation(this, R.id.password, regexPassword, R.string.invalid_confirm_password);
        awesomeValidation.addValidation(this, R.id.confpassword, R.id.password, R.string.invalid_password);


    }

    public void haveaccount() {
            startActivity(new Intent(RegisterActivity.this, Login.class));
    }

    private void register(){
        progressBar.setVisibility(View.VISIBLE);

       final String Firstname = firstName.getEditText().getText().toString().trim();
        final String Lastname = lastName.getEditText().getText().toString().trim();
        final String FirstEmail = Email.getEditText().getText().toString().trim();
        final String FirstPassword = Password.getEditText().getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_REGISTER,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);

                        try {

                            JSONObject obj = new JSONObject(response);


                            if (obj.getBoolean("status")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                                JSONObject users = obj.getJSONObject("user");
//                                for (int i = 0; i < array.length(); i++) {
//
//                                    JSONObject users = array.getJSONObject(i);


                                    User user = new User(
                                            users.getInt("id"),
                                            users.getString("first_name"),
                                            users.getString("last_name"),
                                            users.getString("email"),
                                            users.getString("password")
                                    );
                                    SharedPrefManager.getInstance(getApplicationContext()).Login(user);
//                                }

                                finish();
                                startActivity(new Intent(getApplicationContext(), Home.class));
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("first_name", Firstname);
                params.put("last_name", Lastname);
                params.put("email", FirstEmail);
                params.put("password", FirstPassword);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);


    }
}
