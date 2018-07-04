package com.kirigokaranja.alphagym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kirigokaranja.alphagym.Adapters.MyWorkoutRecyclerViewAdapter;
import com.kirigokaranja.alphagym.Model.Workout;

import java.util.ArrayList;
import java.util.List;

public class MyWorkout extends AppCompatActivity {

    List<Workout> lworkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workout);

        lworkout = new ArrayList<>();
        lworkout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        lworkout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        lworkout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        lworkout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        lworkout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        lworkout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        lworkout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        lworkout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        lworkout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        lworkout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        lworkout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        lworkout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));

        RecyclerView rview = (RecyclerView)findViewById(R.id.myworkout_recyclerview);
        MyWorkoutRecyclerViewAdapter madapter = new MyWorkoutRecyclerViewAdapter(this, lworkout);
        rview.setLayoutManager (new LinearLayoutManager(getApplicationContext()));
        rview.setAdapter(madapter);
    }
}
