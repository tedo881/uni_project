/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Raphaël Bussa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package app.uni.my.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HeaderActivity extends AppCompatActivity {
    public static Student student;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    public static DBHandler db;
    public static DBHandlerSubjects dbsub;
    public static DBHandlerscores dbsc;

    public static ArrayList<Exam> exams;
    public String profilePhoto;
    public String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);



         db = new DBHandler(this);
        dbsub = new DBHandlerSubjects(this);
        dbsc = new DBHandlerscores(this);
        exams = db.getExams();

        db = new DBHandler(getApplicationContext());
        student = db.getContact();


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.header));
        toolbar.setLogo(R.drawable.logo);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        profilePhoto="http://sangu.edu.ge/images/logo.png";
        email = student.getEmail();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.addHeaderView(headerView());
        if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }

        setupNavigationDrawerContent(navigationView);

        actionBarDrawerToggle = new ActionBarDrawerToggle(HeaderActivity.this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                actionBarDrawerToggle.syncState();
            }
        });
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        StudentFragment myf = new StudentFragment();

      getSupportFragmentManager().beginTransaction().replace(R.id.myFrame, myf).commit();



    }

    private HeaderView headerView() {
        HeaderView headerView = new HeaderView(HeaderActivity.this, false);
        headerView.background().setBackgroundColor(getResources().getColor(R.color.material_grey_600));
        Picasso.with(HeaderActivity.this)
                .load(R.drawable.uni)
                .into(headerView.background());
        Picasso.with(HeaderActivity.this)
                .load(R.drawable.logo2)
                .into(headerView.avatar());
        headerView.username(student.getName() + " " + student.getSurname());
        headerView.email(student.getEmail());
        headerView.setOnHeaderClickListener(new HeaderInterface.OnHeaderClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        headerView.setOnAvatarClickListener(new HeaderInterface.OnAvatarClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        headerView.setArrow(new HeaderInterface.OnArrowClickListener() {
            @Override
            public void onClick(View view) {

                if (isConnectingToInternet())
                refresh();
               else
                    Toast.makeText(HeaderActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
        return headerView;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_inbox:
                                menuItem.setChecked(true);
                                StudentFragment myf = new StudentFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.myFrame, myf).commit();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;

                            case R.id.item_navigation_drawer_starred:
                                menuItem.setChecked(true);
                                ScoreFragment mysc = new ScoreFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.myFrame, mysc).commit();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;

                            case R.id.item_navigation_drawer_sent_mail:
                                menuItem.setChecked(true);
                                ExamFragment myEx = new ExamFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.myFrame, myEx).commit();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_drafts:
                                menuItem.setChecked(true);
                                SubjectFragment mysch = new SubjectFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.myFrame, mysch).commit();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.exit:
                                menuItem.setChecked(true);
                                getApplicationContext().deleteDatabase(db.getDatabaseName());
                                getApplicationContext().deleteDatabase(dbsc.getDatabaseName());
                                getApplicationContext().deleteDatabase(dbsub.getDatabaseName());


                                Toast.makeText(getApplicationContext(), "უკან დაბრუნება", Toast.LENGTH_SHORT).show();
                                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                SharedPreferences.Editor editor = sp.edit();
                                editor.clear();
                                editor.commit();

                                Intent i = new Intent(getApplicationContext(), Main.class);
                                startActivity(i);

                                finish();

                                return true;


                        }
                        return true;
                    }
                });
    }


    public static void addStudentInfo(Context context) {


        DBHandler db = new DBHandler(context);
        student = db.getContact();

//String myinfo="სახელი -- "+student.getName()+"\n"+"გვარი ---"+student.getSurname();
        //info.setText(myinfo);


    }

    public void refresh(){

        db = new DBHandler(getApplicationContext());
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String id=sp.getString("id", "");
        String password=sp.getString("password", "");


        getApplicationContext().deleteDatabase(db.getDatabaseName());
        getApplicationContext().deleteDatabase(dbsc.getDatabaseName());
        getApplicationContext().deleteDatabase(dbsub.getDatabaseName());
        new LogIn(getBaseContext()).execute(id, password);



    }
    public boolean isConnectingToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }
}