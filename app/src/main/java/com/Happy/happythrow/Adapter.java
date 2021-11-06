package com.Happy.happythrow;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.Holder>{
    @NonNull
    @Override
    public Adapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}



class Holder extends RecyclerView.ViewHolder {


    public Holder(@NonNull View itemView) {
        super(itemView);

        });
    }
}

}