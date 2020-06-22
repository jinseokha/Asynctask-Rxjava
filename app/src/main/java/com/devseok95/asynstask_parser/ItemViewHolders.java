package com.devseok95.asynstask_parser;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolders extends RecyclerView.ViewHolder {

    LinearLayout layout;
    TextView tvDate;
    TextView tvTitle;
    TextView tvCountry;

    public ItemViewHolders(View itemView) {
        super(itemView);

        layout = itemView.findViewById(R.id.layout);
        tvDate = itemView.findViewById(R.id.textViewDate);
        tvTitle = itemView.findViewById(R.id.textViewTitle);
        tvCountry = itemView.findViewById(R.id.textViewCountry);


    }
}
