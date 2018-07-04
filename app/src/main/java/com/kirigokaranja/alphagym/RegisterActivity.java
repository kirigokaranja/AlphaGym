package com.kirigokaranja.alphagym;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.kirigokaranja.alphagym.Api.ApiInterface;
import com.kirigokaranja.alphagym.Api.ApiUrl;
import com.kirigokaranja.alphagym.Model.Results;
import com.kirigokaranja.alphagym.Model.User;
import com.kirigokaranja.alphagym.SharedPref.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout firstName;
    private TextInputLayout lastName;
    private TextInputLayout Email;
    private TextInputLayout Password;
    private TextInputLayout confirmPassword;
    private Button create;

    public AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

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

        String regexPassword = ".{6,}";
        awesomeValidation.addValidation(this, R.id.password, regexPassword, R.string.invalid_confirm_password);
        awesomeValidation.addValidation(this, R.id.confpassword, R.id.password, R.string.invalid_password);


    }

//    public void gonext() {
//            startActivity(new Intent(RegisterActivity.this, MoreInformation.class));
//    }

    private void register(){


       String Firstname = firstName.getEditText().getText().toString().trim();
        String Lastname = lastName.getEditText().getText().toString().trim();
        String FirstEmail = Email.getEditText().getText().toString().trim();
        String FirstPassowrd = Password.getEditText().getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);

        User user = new User(Firstname, Lastname, FirstEmail, FirstPassowrd);

        Call<Results> call = service.register(
                user.getFirst_name(),
                user.getLast_name(),
                user.getEmail(),
                user.getPassword()
        );

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {

                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

              //  if (!response.body().getStatus()){

                    finish();
                    SharedPrefManager.getInstance(getApplicationContext()).Login(response.body().getUser());
                    startActivity(new Intent(getApplicationContext(), Home.class));
              //  }

            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
