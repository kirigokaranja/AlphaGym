package com.kirigokaranja.alphagym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LogWorkout extends AppCompatActivity {

    List<Workout> Workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_workout);

        Workout = new ArrayList<>();
        Workout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        Workout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        Workout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        Workout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        Workout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        Workout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        Workout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        Workout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        Workout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        Workout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        Workout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        Workout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        Workout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));
        Workout.add(new Workout("Push Up", "10/10/1997", "ten","five", "zero", R.drawable.bck));

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.logworkout_recyclerview);
        LogWorkoutRecyclerViewAdapter adapter = new LogWorkoutRecyclerViewAdapter(this, Workout);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);
    }
}
