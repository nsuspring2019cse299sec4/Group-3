package com.example.khujo.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.khujo.R;

public class ListViewHolder extends RecyclerView.ViewHolder {

    public TextView textViewListName, textViewListId;
    View itemView;
    public Button btnShow;

    public ListViewHolder(View view) {
        super(view);
        itemView = view;

        textViewListName = view.findViewById(R.id.textViewListName);
        textViewListId = view.findViewById(R.id.textViewListId);
        btnShow = view.findViewById(R.id.btnShow);


    }
}
