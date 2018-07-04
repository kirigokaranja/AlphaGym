package com.kirigokaranja.alphagym.Classes;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kirigokaranja.alphagym.R;

public class InstructorDetails extends AppCompatActivity {

    private TextView name , bio, contact, email;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_details);

        name = (TextView)findViewById(R.id.nameDetails);
        bio = (TextView)findViewById(R.id.bioDetails);
        contact = (TextView)findViewById(R.id.contactDetails);
        email = (TextView)findViewById(R.id.emailDetails);
        image = (ImageView)findViewById(R.id.imageThumbnail);

        Intent intent = getIntent();
        String Name = intent.getExtras().getString("Name");
        //String Exercise = intent.getExtras().getString("Exercise");
        String Bio = intent.getExtras().getString("Bio");
        String Contact = intent.getExtras().getString("Contact");
        String Email = intent.getExtras().getString("Email");
        int Thumbnail = intent.getExtras().getInt("Thumbnail");

        name.setText(Name);
        bio.setText(Bio);
        contact.setText(Contact);
        email.setText(Email);
        image.setImageResource(Thumbnail);

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(v);
            }
        });

    }

    private void call(View v) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + contact.getText().toString()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        startActivity(callIntent);
    }
}
