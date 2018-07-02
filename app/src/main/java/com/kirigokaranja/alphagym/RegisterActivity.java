package com.kirigokaranja.alphagym;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

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
                    gonext();
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

    public void gonext() {
            startActivity(new Intent(RegisterActivity.this, MoreInformation.class));
    }
}
