package com.Happy.happythrow;



import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {



    public Adapter() {

    }


    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.my_trashitem, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {



    }

    @Override
    public int getItemCount() {

        return date.size();
    }

    class Holder extends RecyclerView.ViewHolder {



        public Holder(@NonNull View itemView) {
            super(itemView);


        }
    }

}
