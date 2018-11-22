package com.sorinaidea.ghaichi.layout;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sorinaidea.ghaichi.R;
import com.sorinaidea.ghaichi.adapter.ReservationAdabper;
import com.sorinaidea.ghaichi.model.Reservation;
import com.sorinaidea.ghaichi.util.FontManager;

import java.util.ArrayList;
import java.util.Date;

public class UserReservationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserReservationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserReservationFragment newInstance(String param1, String param2) {
        UserReservationFragment fragment = new UserReservationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservation, container, false);
    }

    private RecyclerView recReservations;
    private Button btnReserve;

    private Typeface fontMaterialIcons;
    private Typeface fontIranSans;


    private ArrayList<Reservation> initDataset() {
        ArrayList<Reservation> mDataset = new ArrayList<>();
        Date date = new Date();
        for (int i = 0; i < 20; i++) {
            mDataset.add(new Reservation(R.drawable.preview_small,"نام آرایشگاه","1396/11/12","16:45 بعد از ظهر","لیست مختصر خدمات..."));
        }
        return mDataset;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {

        fontMaterialIcons = FontManager.getTypeface(getContext(), FontManager.MATERIAL_ICONS);
        fontIranSans = FontManager.getTypeface(getContext(), FontManager.IRANSANS_TEXTS);


        recReservations  = (RecyclerView) view.findViewById(R.id.recReservations);
        recReservations.setNestedScrollingEnabled(false);
        recReservations.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recReservations.setAdapter(new ReservationAdabper(initDataset(), getContext()));


    }

    // TODO: Rename method, add argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



}