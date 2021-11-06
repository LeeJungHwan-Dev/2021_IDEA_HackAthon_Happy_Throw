package com.Happy.happythrow;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    ArrayList<String> nameList;
    ArrayList<String> howfullList;
    ArrayList<String> fullnumList;

    public Adapter(ArrayList<String> name, ArrayList<String> howfull,ArrayList<String> fullnum ) {
        this.nameList = name;
        //this.howfullList = howfull;
        this.fullnumList = fullnum;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.my_trashitem, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.name.setText(nameList.get(position));
        //holder.howfull.setText(howfullList.get(position));
        holder.fullnum.setText(fullnumList.get(position));
    }

    @Override
    public int getItemCount() {

        return nameList.size();

    }

    class Holder extends RecyclerView.ViewHolder {

        TextView name;
        TextView howfull;
        TextView fullnum;


        public Holder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            howfull = itemView.findViewById(R.id.howfull);
            fullnum = itemView.findViewById(R.id.fullnum);
        }
    }
}