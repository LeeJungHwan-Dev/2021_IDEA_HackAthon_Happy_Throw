package com.Happy.happythrow;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    ArrayList<String> nameList;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public Adapter(ArrayList<String> name ) {
        this.nameList = name;
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

            db.collection("trash").document(nameList.get(position)).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    holder.fullnum.setText("포화도 "+documentSnapshot.get("포화도").toString());
                }
            });



    }

    @Override
    public int getItemCount() {

        return nameList.size();

    }

    class Holder extends RecyclerView.ViewHolder {

        TextView name;
        TextView fullnum;


        public Holder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            fullnum = itemView.findViewById(R.id.fullnum);
        }
    }
}