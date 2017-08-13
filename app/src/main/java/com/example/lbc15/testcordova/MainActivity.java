package com.example.lbc15.testcordova;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.lbc15.testcordova.components.AnuWebview;
import com.example.lbc15.testcordova.utils.Logger;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectPage("page1");
                    return true;
                case R.id.navigation_dashboard:
                    selectPage("page2");
                    return true;
                case R.id.navigation_notifications:
                    selectPage("page3");
                    return true;
            }
            return false;
        }

    };

    private String LastPageId = "page1";
    private HashMap<String, FrameLayout> pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Logger.i("drawer opened");
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        PageFragmentPagerAdapter adapter = new PageFragmentPagerAdapter(getSupportFragmentManager(),
                this);
        viewPager.setAdapter(adapter);

        //TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.table_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(1).select();

        pages = new HashMap<String, FrameLayout>();
        pages.put("page1", (FrameLayout) findViewById(R.id.page1));
        pages.put("page2", (FrameLayout) findViewById(R.id.page2));
        pages.put("page3", (FrameLayout) findViewById(R.id.page3));

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        
    }

    private void selectPage(String id) {
        pages.get(LastPageId).setVisibility(View.INVISIBLE);
        LastPageId = id;
        pages.get(LastPageId).setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            Logger.i("close drawer");
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Logger.i("on Back");
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = null;

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            Logger.i("start tool activity");
            intent = new Intent(getApplicationContext(), ToolActivity.class);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        if (intent != null) {
            startActivity(intent);
            return false;
        }
        return true;
    }
}
