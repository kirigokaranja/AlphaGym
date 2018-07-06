package com.kirigokaranja.alphagym;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kirigokaranja.alphagym.Adapters.GymInstructoRecycleViewAdapter;
import com.kirigokaranja.alphagym.Model.Instructors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GymInstructor extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GymInstructoRecycleViewAdapter adapter;

    private List<Instructors> instructorList;
    private static final String URL = "http://alphagymapplication.herokuapp.com/api/instructors";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_instructor);


        recyclerView = (RecyclerView)findViewById(R.id.instructor_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        instructorList = new ArrayList<>();
        
        showInstructors();

    }

    private void showInstructors() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET
                ,URL
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONArray array = new JSONArray(response);

                    //traversing through all the object
                    for (int i = 0; i < array.length(); i++) {

                        //getting product object from json array
                        JSONObject instructor = array.getJSONObject(i);


                             String name =  instructor.getString("name");
                        String gender =  instructor.getString("gender");
                        String contact =  instructor.getString("contact");
                        String email =  instructor.getString("email");
                        String bio =  instructor.getString("bio");
                        String image =  instructor.getString("image");
                        int id =  instructor.getInt("id");
                        int gymid =  instructor.getInt("gym_id");

                        Instructors instructors = new Instructors(name, gender,contact, email, bio, image, id, gymid);
                        instructorList.add(instructors);

                    }

                    //creating adapter object and setting it to recyclerview
                    adapter = new GymInstructoRecycleViewAdapter(GymInstructor.this, instructorList);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GymInstructor.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
}