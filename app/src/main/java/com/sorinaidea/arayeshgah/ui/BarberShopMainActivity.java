package com.sorinaidea.arayeshgah.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.sorinaidea.arayeshgah.R;
import com.sorinaidea.arayeshgah.adapter.ImageSliderAdapter;
import com.sorinaidea.arayeshgah.adapter.ReservationAdabper;
import com.sorinaidea.arayeshgah.layout.HomePageFragment;
import com.sorinaidea.arayeshgah.model.BarberShop;
import com.sorinaidea.arayeshgah.model.Reservation;
import com.sorinaidea.arayeshgah.util.FontManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by mr-code on 4/8/2018.
 */

public class BarberShopMainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private TextView txtUserInfo;
    private TextView txtCity;
    private Typeface fontIranSans;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_setting) {
            Intent intent = new Intent(BarberShopMainActivity.this, SettingActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_aboutus) {
            Intent intent = new Intent(BarberShopMainActivity.this, AboutUsActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_faq) {
            Intent intent = new Intent(BarberShopMainActivity.this, FaqActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_profile) {
            Intent intent = new Intent(BarberShopMainActivity.this, UserProfileActivity.class);
            startActivity(intent);
        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private DrawerLayout drawer;
    FloatingActionButton fabAddServiceCategory;
    FloatingActionButton fabAddService;
    RecyclerView recReservations;
    private ViewPager mPager;
    private CircleIndicator indicator;
    private static int currentPage = 0;
    private NestedScrollView scrViewRoot;

    private ArrayList<Reservation> initDataset() {
        ArrayList<Reservation> mDataset = new ArrayList<>();
        Date date = new Date();
        for (int i = 0; i < 20; i++) {
            mDataset.add(new Reservation(R.drawable.background_green, "نام کاربر", "1396/11/12", "16:45 ب.ظ", "لیست مختصر خدمات..."));
        }
        return mDataset;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barbershop_admin);

        fabAddServiceCategory = (FloatingActionButton) findViewById(R.id.fabAddServiceCategory);
        fabAddService = (FloatingActionButton) findViewById(R.id.fabAddService);
        scrViewRoot = (NestedScrollView) findViewById(R.id.scrViewRoot);


        fabAddServiceCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BarberShopMainActivity.this, AddServiceCategoryActivity.class);
                startActivity(intent);
            }
        });


        fabAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BarberShopMainActivity.this, AddServiceActivity.class);
                startActivity(intent);
            }
        });


        recReservations = (RecyclerView) findViewById(R.id.recReservations);
        recReservations.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recReservations.setAdapter(new ReservationAdabper(initDataset(), getApplicationContext()));
        recReservations.setNestedScrollingEnabled(false);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        mPager = (ViewPager) findViewById(R.id.pager);
        indicator = (CircleIndicator) findViewById(R.id.indicator);
        fontIranSans = FontManager.getTypeface(getApplicationContext(), FontManager.IRANSANS_TEXTS);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("پنل آرایشگر");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_menu_open, R.string.navigation_menu_close);


        recReservations.setFocusable(false);

        scrViewRoot.fullScroll(ScrollView.FOCUS_UP);
        scrViewRoot.smoothScrollTo(0, 0);


        drawer.setDrawerListener(toggle);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);


        txtUserInfo = (TextView) navigationView.getHeaderView(0).findViewById(R.id.txtUserInfo);
        txtCity = (TextView) navigationView.getHeaderView(0).findViewById(R.id.txtCity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            navigationView.setLayoutDirection(NavigationView.LAYOUT_DIRECTION_RTL);
        }
        navigationView.setNavigationItemSelectedListener(this);
        disableNavigationViewScrollbars(navigationView);


        txtUserInfo.setText("کاربر آرایشگر");
        txtCity.setText("سنندج");

        initializeImageSlider();

        FontManager.setFont(txtCity, fontIranSans);
        FontManager.setFont(txtUserInfo, fontIranSans);
    }


    private static final List<String> images = Arrays.asList(
            "asdasd",
            "asdasd",
            "asdasd",
            "asdasd"
    );

    private ArrayList<String> imageList = new ArrayList<>();

    private void hideToolbar() {
        toolbar.animate().translationY(-256).setInterpolator(new AccelerateInterpolator()).start();
    }

    private void showToolbar() {
        toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
    }

    private void initializeImageSlider() {

        imageList.addAll(images);
        ImageSliderAdapter adapter = new ImageSliderAdapter(getApplicationContext(), imageList);
        mPager.setAdapter(adapter);
        indicator.setViewPager(mPager);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == images.size()) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };


        adapter.setImageOnCLickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!show) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToolbar();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideToolbar();
                        }
                    });
                }
                show = !show;
            }
        });

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }

    private boolean show = false;

    private void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        super.onBackPressed();
    }


}