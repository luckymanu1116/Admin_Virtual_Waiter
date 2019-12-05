package com.example.adminvirtualwaiter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Adminhome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhome);
        Fragment newFragment4 = new Dashboardhm();
        FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
        ft4.replace(R.id.mainframe_frame,newFragment4);
        ft4.commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("MissingPermission")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId())
            {
                case R.id.dashboard:
                    Fragment newFragment = new Dashboardhm();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.mainframe_frame,newFragment);
                    ft.commit();
                    break;
                case R.id.AddItems:
                    Fragment newFragment2 = new Addingitems();
                    FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                    ft2.replace(R.id.mainframe_frame,newFragment2);
                    ft2.commit();
                    break;
                case R.id.Feedback:
                    Fragment newFragment3 = new Feedback();
                    FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                    ft3.replace(R.id.mainframe_frame,newFragment3);
                    ft3.commit();
                    break;
                case R.id.Profiel:
                    Fragment newFragment4 = new Profile();
                    FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                    ft4.replace(R.id.mainframe_frame,newFragment4);
                    ft4.commit();
                    break;
                default:
                    Fragment newFragment5= new Dashboardhm();
                    FragmentTransaction ft5 = getSupportFragmentManager().beginTransaction();
                    ft5.replace(R.id.mainframe_frame,newFragment5);
                    ft5.commit();
                    break;

            }
            return true;
        }
    };
}
