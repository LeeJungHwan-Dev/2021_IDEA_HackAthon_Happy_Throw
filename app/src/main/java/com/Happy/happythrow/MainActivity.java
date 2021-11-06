package com.Happy.happythrow;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Adapter;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.flags &= ~WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);


        File file = new File(getFilesDir(),"id.txt");


        if(file.exists()) {
            Intent intent = new Intent(MainActivity.this, adduser.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            finish();
        }
        else {
            Intent intent = new Intent(MainActivity.this, QR_main.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            finish();
        }
    }
}