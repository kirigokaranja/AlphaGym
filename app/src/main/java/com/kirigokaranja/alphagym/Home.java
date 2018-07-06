package com.kirigokaranja.alphagym;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.kirigokaranja.alphagym.Model.User;
import com.kirigokaranja.alphagym.SharedPref.SharedPrefManager;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        TextView name = (TextView)findViewById(R.id.profileName);
        TextView username = (TextView)findViewById(R.id.user_names);
        User user = SharedPrefManager.getInstance(this).getUser();
        String names = user.getFirst_name()+ user.getLast_name();
        name.setText(names);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.english) {
            return true;
        }else if (id == R.id.kiswahili){

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(this,Home.class));
        } else if (id == R.id.nav_workout) {
            startActivity(new Intent(this,MyWorkout.class));
        } else if (id == R.id.nav_location) {
            startActivity(new Intent(this,HomeGym.class));
        } else if (id == R.id.nav_log) {
            startActivity(new Intent(this,LogWorkout.class));
        } else if (id == R.id.nav_instructor) {
            startActivity(new Intent(this,GymInstructor.class));
        } else if (id == R.id.nav_profile) {
            startActivity(new Intent(this,Profile.class));
        } else if (id == R.id.nav_logout) {
            SharedPrefManager.getInstance(getApplicationContext()).logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void GymInstructors(View view) {
        startActivity(new Intent(this,GymInstructor.class));
    }

    public void myProfile(View view) {
        startActivity(new Intent(this,Profile.class));
    }

    public void logWorkout(View view) {
        startActivity(new Intent(this,LogWorkout.class));
    }

    public void MyWorkout(View view) {
        startActivity(new Intent(this,MyWorkout.class));
    }

    public void GymLocations(View view) {
        startActivity(new Intent(this,HomeGym.class));
    }

}
