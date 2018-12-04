package com.sorinaidea.ghaichi.adapter.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sorinaidea.ghaichi.R;
import com.sorinaidea.ghaichi.fast.BarbershopCard;
import com.sorinaidea.ghaichi.ui.BarberShopActivity;
import com.sorinaidea.ghaichi.util.FontManager;
import com.sorinaidea.ghaichi.webservice.API;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;


/**
 * Created by mr-code on 5/6/2018.
 */

public class NearestBarbershopsAdapter extends RecyclerView.Adapter<NearestBarbershopsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<BarbershopCard> mItems;
    Typeface fontIransans;

    public NearestBarbershopsAdapter(Context mContext,
                                     ArrayList<BarbershopCard> mItems) {

        this.mItems = mItems;
        this.mContext = mContext;
        fontIransans = FontManager.getTypeface(mContext, FontManager.IRANSANS_TEXTS);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final View view;
        private final ImageView imgLogo;
        private final TextView txtName;
        private final TextView txtDistance;


        public ViewHolder(View view) {
            super(view);
            this.view = view;
            imgLogo = (ImageView) view.findViewById(R.id.imgLogo);
            txtName = (TextView) view.findViewById(R.id.txtName);
            txtDistance = (TextView) view.findViewById(R.id.txtDistance);

        }

        public TextView getTxtName() {
            return txtName;
        }

        public View getView() {
            return view;
        }

        public ImageView getImgLogo() {
            return imgLogo;
        }

        public TextView getTxtDistance() {
            return txtDistance;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.bshops_nearest, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final BarbershopCard barberShop = mItems.get(position);
        try {

            API.getPicasso(mContext)
                    .load(API.BASE_URL
                            + URLDecoder.decode(barberShop.getIcon(), "UTF-8"))
                    .into(holder.imgLogo);

        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.getTxtName().setText(barberShop.getName());
        holder.getTxtDistance().setText( ( (int)(Math.random()*5000) )+ " متر");
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BarberShopActivity.class);
                intent.putExtra("BARBERSHOP_ID", Integer.toString(barberShop.getId()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        FontManager.setFont(holder.getTxtName(), fontIransans);
        FontManager.setFont(holder.getTxtDistance(), fontIransans);

    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
