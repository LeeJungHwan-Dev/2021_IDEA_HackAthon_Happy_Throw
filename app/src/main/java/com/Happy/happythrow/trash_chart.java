package com.Happy.happythrow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class trash_chart extends AppCompatActivity {

    /**
     * 이곳에
     * (사용할 아이템) 변수 이름;
     * 형태로 지정한 다음 아래 참고.
     */

    ImageButton Gotrash,GoMyBin,Gosetting;
    BarChart barChart;
    Integer [] valueArray = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference productRef = db.collection("hwtrash").document("id");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash_chart);

        /**
         * xml과 자바 코드를 연결 하는 작업니다.
         * 아래와 같이 위에 선언한 변수를 findViewById(R.id.~~~~) 식으로 xml과 연결
         */

        GoMyBin = findViewById(R.id.Go_MyTrash_button);
        Gosetting = findViewById(R.id.Go_setting_button);
        Gotrash = findViewById(R.id.Go_Trash_button);
        barChart = (BarChart)findViewById(R.id.bar_chart);

        /**
         * 아래 코드는 건들지 마시오.
         * 레이아웃을 다시 그려주는 코드 입니다.
         */
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.flags &= ~WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);

        /**--
         * 이 아래로 코드를 작성해주세요.
         */

        productRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();
                List<String> value = (List<String>) document.get("2021");
                for(int i=0; i<12; i++) {
                    valueArray[i] = Integer.parseInt(value.get(i));
                }

                BarChartGraph();
                barChart.setTouchEnabled(false); // 확대불가
                barChart.getAxisRight().setAxisMaxValue(120);
                barChart.getAxisLeft().setAxisMaxValue(120);
            }
        });

        GoMyBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 이 코드는 인텐트를 활용하여 startActivity로 인텐트를 실행한다.
                 * 실행된 인텐트는 trash.this 에서 my_trash.class로 이동한다.
                 *  overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                 *  으로 페이드인 , 페이드 아웃 효과를 줌
                 *  finish()로 액티비티 종료(해당창 종료)
                 * */

                Intent intent = new Intent(trash_chart.this,my_trash.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });

        Gosetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 이 코드는 인텐트를 활용하여 startActivity로 인텐트를 실행한다.
                 * 실행된 인텐트는 trash.this 에서 setting.class로 이동한다.
                 *  overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                 *  으로 페이드인 , 페이드 아웃 효과를 줌
                 *  finish()로 액티비티 종료(해당창 종료)
                 * */

                Intent intent = new Intent(trash_chart.this,setting.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });

        Gotrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 이 코드는 인텐트를 활용하여 startActivity로 인텐트를 실행한다.
                 * 실행된 인텐트는 trash.this 에서 QR_main.class로 이동한다.
                 *  overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                 *  으로 페이드인 , 페이드 아웃 효과를 줌
                 *  finish()로 액티비티 종료(해당창 종료)
                 * */

                Intent intent = new Intent(trash_chart.this,QR_main.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });
    }

    private void BarChartGraph() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i=0; i< 12; i++) {
            entries.add(new BarEntry(i+1, (Integer) valueArray[i]));
        }

        BarDataSet depenses = new BarDataSet(entries, "쓰레기 배출량 단위: kg");
        depenses.setAxisDependency(YAxis.AxisDependency.LEFT);
        barChart.setDescription(null);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setLabelCount(12);

        YAxis yLAxis = barChart.getAxisLeft();
        yLAxis.setAxisMinimum(0);

        YAxis yRAxis = barChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        BarData data = new BarData(depenses);
        depenses.setColors(ColorTemplate.LIBERTY_COLORS);
        data.setValueTextSize(13);
        data.setValueTypeface(Typeface.DEFAULT_BOLD);

        barChart.setData(data);
        barChart.animateXY(120, 120);
        barChart.invalidate();
    }
}