package com.emargystudio.bohemeav0021;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
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
import android.widget.Toast;


import com.emargystudio.bohemeav0021.Orders.OrderFragment;
import com.emargystudio.bohemeav0021.helperClasses.SectionsPagerAdapter;
import com.emargystudio.bohemeav0021.helperClasses.SharedPreferenceManger;

import com.facebook.login.LoginManager;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;


import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "HomeActivity";


    //views
    CircleImageView userPhoto;
    TextView userName;
    private ViewPager mViewPager;


    //Server Side
    ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!SharedPreferenceManger.getInstance(this).isUserLogggedIn()){

            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }else {

            setContentView(R.layout.activity_home);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);



            //var
            user = ParseUser.getCurrentUser();
            mViewPager = (ViewPager) findViewById(R.id.viewpager_container);


            //menu setup
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            //add user photo and name to menu
            View headerView = navigationView.getHeaderView(0);
            userPhoto = headerView.findViewById(R.id.user_photo);
            userName = headerView.findViewById(R.id.user_name);
            userName.setText(user.getUsername());
            userName.setTextSize(16f);
            Picasso.get().load(user.getString("image")).into(userPhoto);
            //change menu title color and text size
            Menu menu = navigationView.getMenu();
            MenuItem tools = menu.findItem(R.id.tools);
            SpannableString s = new SpannableString(tools.getTitle());
            s.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s.length(), 0);
            tools.setTitle(s);
            navigationView.setNavigationItemSelectedListener(this);

            setupViewPager();


        }
    }

    private void logUserOut(){
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
        // logging out of Facebook
        LoginManager.getInstance().logOut();
        // logging out of Parse
        ParseUser.logOut();
        SharedPreferenceManger.getInstance(this).logUserOut();
        Toast.makeText(this, "Good bye .....", Toast.LENGTH_SHORT).show();

    }


    private void setupViewPager() {

        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CinemaFragment()); //index 0
        adapter.addFragment(new HomeFragment()); //index 1
        adapter.addFragment(new OrderFragment()); //index 2
        mViewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_armchair_silhouette);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_menu_send);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_calendar_today_24px);

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

        if (id == R.id.nav_logout){

            logUserOut();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!SharedPreferenceManger.getInstance(this).isUserLogggedIn()){

            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        mViewPager.setCurrentItem(1);
    }


}
