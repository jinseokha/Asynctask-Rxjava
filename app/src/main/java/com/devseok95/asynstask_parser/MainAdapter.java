package com.devseok95.asynstask_parser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<ItemViewHolders>{
    private Context mContext;
    private List<SafeArt> mSafeInfoList = new ArrayList<>();

    private MainActivity mParentActivity;

    @Override
    public ItemViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams")
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemViewHolders(layoutView);
    }

    public MainAdapter(Context context, List<SafeArt> arrayList, MainActivity activity) {
        this.mContext = context;
        this.mSafeInfoList = arrayList;
        this.mParentActivity = activity;
    }

    @Override
    public int getItemCount() {
        return mSafeInfoList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolders holder, final int position) {
        SafeArt safeList = mSafeInfoList.get(position);

        holder.tvCountry.setText(safeList.countryName);
        holder.tvTitle.setText(safeList.title);
        holder.tvDate.setText(safeList.wrtDt);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mParentActivity.goPageActivity(position);
            }
        });


    }

}
