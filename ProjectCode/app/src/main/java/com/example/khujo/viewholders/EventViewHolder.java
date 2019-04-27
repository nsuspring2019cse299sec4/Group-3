package com.example.khujo.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.khujo.R;

public class EventViewHolder extends RecyclerView.ViewHolder {

    public TextView textViewEventName, textViewEventId;
    View itemView;
    public Button btnDelete, btnShow;

    public EventViewHolder(View view) {
        super(view);
        itemView = view;

        textViewEventName = view.findViewById(R.id.textViewEventName);
        textViewEventId = view.findViewById(R.id.textViewEventId);
        btnShow = view.findViewById(R.id.btnShow);
        btnDelete = view.findViewById(R.id.btnDelete);
    }
}
