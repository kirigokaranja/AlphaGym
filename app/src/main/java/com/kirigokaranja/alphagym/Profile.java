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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kirigokaranja.alphagym.Adapters.MyWorkoutRecyclerViewAdapter;
import com.kirigokaranja.alphagym.Model.User;
import com.kirigokaranja.alphagym.Model.Workout;
import com.kirigokaranja.alphagym.SharedPref.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {

    TextInputLayout Gender, Age, Height, Weight, DesiredWeight, Homegym;
    Button submit;
    String id;
    private static final String URL = "https://alphagymapplication.herokuapp.com/api/user/";
    private static final String URL2 = "http://alphagymapplication.herokuapp.com/api/Profile/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Gender = (TextInputLayout)findViewById(R.id.CustomerGender);
        Age = (TextInputLayout)findViewById(R.id.CustomerAge);
        Height = (TextInputLayout)findViewById(R.id.CustomerHeight);
        Weight = (TextInputLayout)findViewById(R.id.CustomerWeight);
        DesiredWeight = (TextInputLayout)findViewById(R.id.CustomerDesiredWeight);
        Homegym = (TextInputLayout)findViewById(R.id.CustomerGym);
        submit = (Button)findViewById(R.id.btnsendp);

        User user = SharedPrefManager.getInstance(this).getUser();
        id = String.valueOf(user.getId());

        getprofile();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateprofile();
            }
        });
    }

    private void getprofile() {
        String URL_ADD = URL2+id;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_ADD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {


                        JSONObject workout = new JSONObject(response);

                        String gender =  workout.getString("gender");
                        String age =  workout.getString("dob");
                        String height =  workout.getString("height");
                        String weight =  workout.getString("weight");
                        String desired =  workout.getString("desired_weight");
                        String home =  workout.getString("homegym");

                        Gender.getEditText().setText(gender);
                        Age.getEditText().setText(age);
                        Height.getEditText().setText(height);
                        Weight.getEditText().setText(weight);
                        DesiredWeight.getEditText().setText(desired);
                        Homegym.getEditText().setText(home);



                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Profile.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void updateprofile() {
          final String URL_PROFILE = URL+id;


        final String Firstages = Age.getEditText().getText().toString().trim();
        final String Firstgender = Gender.getEditText().getText().toString().trim();
        final String Firstheight = Height.getEditText().getText().toString().trim();
        final String Firstweight = Weight.getEditText().getText().toString().trim();
        final String Firstdweight = DesiredWeight.getEditText().getText().toString().trim();
        final String Firstgym = Homegym.getEditText().getText().toString().trim();
        final String FirstUser = id.trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PROFILE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject obj = new JSONObject(response);

                    if (!obj.getBoolean("status")) {

                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();


                    }else {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                    }
                    startActivity(new Intent(getApplicationContext(), Profile.class));
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("dob", Firstages );
                params.put("user_id", FirstUser);
                params.put("gender", Firstgender);
                params.put("homegym", Firstgym);
                params.put("height", Firstheight);
                params.put("weight", Firstweight);
                params.put("desired_weight", Firstdweight);
                params.put("profilePhoto", "user");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
