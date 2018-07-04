package com.kirigokaranja.alphagym;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kirigokaranja.alphagym.Api.ApiInterface;
import com.kirigokaranja.alphagym.Api.ApiUrl;
import com.kirigokaranja.alphagym.Model.Results;
import com.kirigokaranja.alphagym.SharedPref.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    private TextInputLayout Email;
    private TextInputLayout Password;
    private Button Login;

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

                //s();
                logins();
            }
        });
    }


    public void signup(View view) {
        startActivity(new Intent(this,RegisterActivity.class));
    }
//    public void s() {
//        startActivity(new Intent(this,Home.class));
//    }
    public void logins(){

        String LoginEmail = Email.getEditText().getText().toString().trim();
        String LoginPassowrd = Password.getEditText().getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);


        Call<Results> call = service.login(LoginEmail, LoginPassowrd);

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {

                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
//                s();

                if (!response.body().getStatus()){

                    finish();
                   // SharedPrefManager.getInstance(getApplicationContext()).Login(response.body().getUser());
                    startActivity(new Intent(getApplicationContext(), Home.class));
                }

            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}
