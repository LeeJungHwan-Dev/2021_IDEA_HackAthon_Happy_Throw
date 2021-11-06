package com.Happy.happythrow;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    ArrayList<String> nameList;
    ArrayList<String> paths;
    Context context;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public Adapter(ArrayList<String> name , Context context,ArrayList<String> path) {
        this.nameList = name;
        this.context = context;
        this.paths = path;
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

                   if((Integer.parseInt(documentSnapshot.get("포화도").toString()) * 5 / 6) > 100) {
                       holder.fullnum.setText("포화도 " + 100 +" % ");
                   }
                   else{
                       holder.fullnum.setText("포화도 " + (Integer.parseInt(documentSnapshot.get("포화도").toString()) * 5 / 6) +" % ");
                   }
                }
            });


        holder.delbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = position;
                    nameList.remove(pos);
                    notifyDataSetChanged();

                    File file = new File(context.getFilesDir(),paths.get(pos));
                    if (file.exists()){
                        file.delete();
                        Toast.makeText(context,"삭제 완료",Toast.LENGTH_SHORT).show();
                    }
                    else{

                    }

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
        ImageButton delbutton;

        public Holder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            fullnum = itemView.findViewById(R.id.fullnum);
            delbutton = itemView.findViewById(R.id.deletebutton);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();


                    Intent intent = new Intent(context,QR_main.class);
                    intent.putExtra("go",nameList.get(pos));
                    context.startActivity(intent);
                    ((Activity)context).finish();
                }
            });


        }
    }
}