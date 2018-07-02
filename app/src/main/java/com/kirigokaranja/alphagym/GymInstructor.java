package com.kirigokaranja.alphagym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GymInstructor extends AppCompatActivity {

    List<Instructors> instructors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_instructor);

        instructors = new ArrayList<>();
        instructors.add(new Instructors("Shiv GymmIt", "Cardio", "Male", "0713774575",
                "sharon@gmail.com","made in kenya", R.drawable.bck));
        instructors.add(new Instructors("Frank GymmIt", "Cardio", "Male", "0713774575",
                "sharon@gmail.com","made in kenya", R.drawable.icon));
        instructors.add(new Instructors("James Bond", "Cardio", "Male", "0713774575",
                "sharon@gmail.com","made in kenya", R.drawable.gym));
        instructors.add(new Instructors("Sharon Kirigo", "Cardio", "Male", "0713774575",
                "sharon@gmail.com","made in kenya", R.drawable.dumbbell));
        instructors.add(new Instructors("Yoyo Maa", "Cardio", "Male", "0713774575",
                "sharon@gmail.com","made in kenya", R.drawable.dumbbell_training));
        instructors.add(new Instructors("Shiv GymmIt", "Cardio", "Male", "0713774575",
                "sharon@gmail.com","made in kenya", R.drawable.bck));
        instructors.add(new Instructors("Frank GymmIt", "Cardio", "Male", "0713774575",
                "sharon@gmail.com","made in kenya", R.drawable.icon));
        instructors.add(new Instructors("James Bond", "Cardio", "Male", "0713774575",
                "sharon@gmail.com","made in kenya", R.drawable.gym));
        instructors.add(new Instructors("Sharon Kirigo", "Cardio", "Male", "0713774575",
                "sharon@gmail.com","made in kenya", R.drawable.dumbbell));
        instructors.add(new Instructors("Yoyo Maa", "Cardio", "Male", "0713774575",
                "sharon@gmail.com","made in kenya", R.drawable.dumbbell_training));


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.instructor_recyclerview);
        GymInstructoRecycleViewAdapter adapter = new GymInstructoRecycleViewAdapter(this, instructors);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
    }
}
