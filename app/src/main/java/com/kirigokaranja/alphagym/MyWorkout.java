package com.kirigokaranja.alphagym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;

public class MyWorkout extends AppCompatActivity {

    List<Workout> lworkout;
    private RecyclerView rview;
    private MyWorkoutRecyclerViewAdapter madapter;
    private static final String URL = "http://alphagymapplication.herokuapp.com/api/workoutDetails/";
    String muser;
    //final String USER = "3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workout);

     //   muser = (TextView)findViewById(R.id.user_id_my);
        User user = SharedPrefManager.getInstance(this).getUser();
        muser = String.valueOf(user.getId());
      //  muser.setText("3");

        rview = (RecyclerView)findViewById(R.id.myworkout_recyclerview);
        rview.setHasFixedSize(true);
        rview.setLayoutManager(new LinearLayoutManager(this));
//        madapter = new MyWorkoutRecyclerViewAdapter(this, lworkout);
//        rview.setLayoutManager (new LinearLayoutManager(getApplicationContext()));
        rview.setAdapter(madapter);
        lworkout = new ArrayList<>();
        
        workouts();


    }

    private void workouts() {

        String URL_ADD = URL+muser;

        StringRequest stringRequest = new StringRequest(Request.Method.GET
                , URL_ADD
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++){

                        JSONObject workout = array.getJSONObject(i);

                        String date =  workout.getString("date");
                        String name =  workout.getString("exercise_name");
                        String reps =  workout.getString("reps");
                        String sets =  workout.getString("sets");
                        String location =  workout.getString("status");
                        int id =  workout.getInt("id");

                        String nm= "Date: "+date;
                        String rp= "Reps: "+reps;
                        String st = "Sets: "+sets;
                        String lc= "Location: "+location;

                        Workout workouts = new Workout(name, nm, rp, st, lc);
                        lworkout.add(workouts);
                    }

                    madapter = new MyWorkoutRecyclerViewAdapter(MyWorkout.this, lworkout);
                    rview.setAdapter(madapter);

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MyWorkout.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
