package com.kirigokaranja.alphagym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.kirigokaranja.alphagym.Adapters.GymInstructoRecycleViewAdapter;
import com.kirigokaranja.alphagym.Api.ApiInterface;
import com.kirigokaranja.alphagym.Api.ApiUrl;
import com.kirigokaranja.alphagym.Classes.Instructorarray;
import com.kirigokaranja.alphagym.Model.Instructors;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GymInstructor extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GymInstructoRecycleViewAdapter adapter;

    private List<Instructors> instructors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_instructor);


        recyclerView = (RecyclerView)findViewById(R.id.instructor_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new GymInstructoRecycleViewAdapter(instructors, this);

        recyclerView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);

        Call<Instructorarray> call = service.getInstructors();
        call.enqueue(new Callback<Instructorarray>() {
            @Override
            public void onResponse(Call<Instructorarray> call, Response<Instructorarray> response) {

                Log.e("MAin", response.body().getInstructors().get(0).toString());
                instructors = response.body().getInstructors();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Instructorarray> call, Throwable t) {
                Log.e("Main", t.toString());
            }
        });


//        instructors = new ArrayList<>();
//        instructors.add(new Instructors("Shiv GymmIt", "Cardio", "Male", "0713774575",
//                "sharon@gmail.com","made in kenya", R.drawable.bck));
//        instructors.add(new Instructors("Frank GymmIt", "Cardio", "Male", "0713774575",
//                "sharon@gmail.com","made in kenya", R.drawable.icon));
//        instructors.add(new Instructors("James Bond", "Cardio", "Male", "0713774575",
//                "sharon@gmail.com","made in kenya", R.drawable.gym));
//        instructors.add(new Instructors("Sharon Kirigo", "Cardio", "Male", "0713774575",
//                "sharon@gmail.com","made in kenya", R.drawable.dumbbell));
//        instructors.add(new Instructors("Yoyo Maa", "Cardio", "Male", "0713774575",
//                "sharon@gmail.com","made in kenya", R.drawable.dumbbell_training));
//        instructors.add(new Instructors("Shiv GymmIt", "Cardio", "Male", "0713774575",
//                "sharon@gmail.com","made in kenya", R.drawable.bck));
//        instructors.add(new Instructors("Frank GymmIt", "Cardio", "Male", "0713774575",
//                "sharon@gmail.com","made in kenya", R.drawable.icon));
//        instructors.add(new Instructors("James Bond", "Cardio", "Male", "0713774575",
//                "sharon@gmail.com","made in kenya", R.drawable.gym));
//        instructors.add(new Instructors("Sharon Kirigo", "Cardio", "Male", "0713774575",
//                "sharon@gmail.com","made in kenya", R.drawable.dumbbell));
//        instructors.add(new Instructors("Yoyo Maa", "Cardio", "Male", "0713774575",
//                "sharon@gmail.com","made in kenya", R.drawable.dumbbell_training));




    }
}
