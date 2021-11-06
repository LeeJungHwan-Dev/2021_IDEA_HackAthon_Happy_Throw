package com.Happy.happythrow;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    Context mContext;



    public Adapter(Context context) {
       this.mContext = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

            holder.tv1.setText("asd123");

    }

    @Override
    public int getItemCount() {

        return 1;
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView tv1;


        public Holder(@NonNull View itemView) {
            super(itemView);


            tv1 = itemView.findViewById(R.id.tv1);

        }
    }
}
