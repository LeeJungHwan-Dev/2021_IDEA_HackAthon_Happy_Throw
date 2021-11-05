package com.Happy.happythrow;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

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


        Intent intent = new Intent(MainActivity.this,QR_main.class);
        startActivity(intent);

    }
}