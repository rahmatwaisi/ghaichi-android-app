package com.sorinaidea.arayeshgah.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import com.sorinaidea.arayeshgah.R;
import com.sorinaidea.arayeshgah.adapter.EmptyAdabper;
import com.sorinaidea.arayeshgah.adapter.ReservationAdabper;
import com.sorinaidea.arayeshgah.model.Reservation;
import com.sorinaidea.arayeshgah.util.FontManager;

import java.util.ArrayList;
import java.util.Date;


public class ReservationActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NestedScrollView container;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_reservation);



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        container = (NestedScrollView) findViewById(R.id.container);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("رزروها");

        fontMaterialIcons = FontManager.getTypeface(getApplicationContext(), FontManager.MATERIAL_ICONS);
        fontIranSans = FontManager.getTypeface(getApplicationContext(), FontManager.IRANSANS_TEXTS);

        container.setFillViewport(true);
        recReservations = (RecyclerView) findViewById(R.id.recReservations);
        recReservations.setNestedScrollingEnabled(false);
        recReservations.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
//        recReservations.setAdapter(new ReservationAdabper(initDataset(), getApplicationContext()));
        recReservations.setAdapter(new EmptyAdabper(getApplicationContext()));
        recReservations.setMinimumHeight(container.getHeight());
    }


    private RecyclerView recReservations;
    private Button btnReserve;

    private Typeface fontMaterialIcons;
    private Typeface fontIranSans;


    private ArrayList<Reservation> initDataset() {
        ArrayList<Reservation> mDataset = new ArrayList<>();
        Date date = new Date();
        for (int i = 0; i < 20; i++) {
            mDataset.add(new Reservation(R.drawable.img_logo_border, "نام کاربر", "1396/11/12", "16:45 بعد از ظهر", "لیست مختصر خدمات..."));
        }
        return mDataset;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}