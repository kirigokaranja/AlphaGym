package com.kirigokaranja.alphagym;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kirigokaranja.alphagym.Classes.DOBDateFragment;
import com.kirigokaranja.alphagym.Model.User;
import com.kirigokaranja.alphagym.SharedPref.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class LogWorkout extends AppCompatActivity {
    TextInputLayout Name, Location,Reps, Sets;
    TextView Date, userId;
    ImageView workoutImg;
    Button submit;
    int id;
    private static final String URL_WORKOUT = "https://alphagymapplication.herokuapp.com/api/session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_workout);

        Calendar cal = Calendar.getInstance();
        processDatePickerResult(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

        Name = (TextInputLayout)findViewById(R.id.workout_name);
        Location = (TextInputLayout)findViewById(R.id.location);
        Reps = (TextInputLayout)findViewById(R.id.no_reps);
        Sets = (TextInputLayout)findViewById(R.id.no_sets);
        userId = (TextView)findViewById(R.id.userid);
        workoutImg = (ImageView)findViewById(R.id.workout_img);
        submit = (Button)findViewById(R.id.btnsend);

        User user = SharedPrefManager.getInstance(this).getUser();
        userId.setText(String.valueOf(user.getId()));
       // userId.setText("3");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWorkout();
            }
        });


    }

    private void addWorkout() {

        final String Firstname = Name.getEditText().getText().toString().trim();
        final String Firstdate = Date.getText().toString().trim();
        final String Firstreps = Reps.getEditText().getText().toString().trim();
        final String Firstsets = Sets.getEditText().getText().toString().trim();
        final String Firstlocation = Location.getEditText().getText().toString().trim();
        final String FirstUser = userId.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_WORKOUT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject obj = new JSONObject(response);

                    if (!obj.getBoolean("status")) {

                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();


                    }else {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                    }
                    startActivity(new Intent(getApplicationContext(), MyWorkout.class));
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
                params.put("date", Firstdate);
                params.put("exname", Firstname );
                params.put("reps", Firstreps);
                params.put("sets", Firstsets);
                params.put("location", Firstlocation);
                params.put("user_id", FirstUser);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);

        String dateMessage = (month_string + "/" + day_string + "/" + year_string);

        Date = (TextView) findViewById(R.id.wdate);
        Date.setText(dateMessage);



    }

    public void showdate(View view) {
        DOBDateFragment fragment = new DOBDateFragment();
        fragment.show(getSupportFragmentManager(), getString(R.string.date_picker));
    }
}
