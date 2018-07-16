package com.sorinaidea.arayeshgah.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.sorinaidea.arayeshgah.R;
import com.sorinaidea.arayeshgah.model.Advertise;
import com.sorinaidea.arayeshgah.util.FontManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mr-code on 3/10/2018.
 */

public class AdvertisementInfoAdabper extends RecyclerView.Adapter<AdvertisementInfoAdabper.ViewHolder> {
    private static final String TAG = "AdvertisementInfoAdabper";

    private ArrayList<JsonObject> mDataSet;
    private Context mContext;
    private Typeface fontIranSans;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatImageView imgIcon;
        private final TextView txtTitle;
        private final TextView txtValue;

        public ViewHolder(View v) {
            super(v);

            txtTitle = (TextView) v.findViewById(R.id.txtTitle);
            txtValue = (TextView) v.findViewById(R.id.txtValue);
            imgIcon = (AppCompatImageView) v.findViewById(R.id.imgIcon);
        }

        public TextView getTxtTitle() {
            return txtTitle;
        }

        public AppCompatImageView getImgIcon() {
            return imgIcon;
        }

        public TextView getTxtValue() {
            return txtValue;
        }
    }


    public AdvertisementInfoAdabper(ArrayList<JsonObject> informations, Context context) {
        mDataSet = informations;
        mContext = context;
        fontIranSans = FontManager.getTypeface(mContext, FontManager.IRANSANS_TEXTS);
        // setting fonts for icons
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.advertise_info_item, viewGroup, false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        FontManager.setFont(viewHolder.getTxtTitle(), fontIranSans);
        FontManager.setFont(viewHolder.getTxtValue(), fontIranSans);

        JsonObject obj = mDataSet.get(position);

        viewHolder.getTxtTitle().setText(obj.get("title").getAsString());
        viewHolder.getTxtValue().setText(obj.get("value").getAsString());
        viewHolder.getImgIcon().setImageResource(obj.get("icon").getAsInt());
    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
