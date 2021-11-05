package com.Happy.happythrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

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


import java.util.ArrayList;

public class trash_chart extends AppCompatActivity {

    /**
     * 이곳에
     * (사용할 아이템) 변수 이름;
     * 형태로 지정한 다음 아래 참고.
     */

    ImageButton Gotrash,GoMyBin,Gosetting;
    BarChart barChart;
    ArrayList<Integer> valueList = new ArrayList<>();
    ArrayList<Integer> labelList = new ArrayList<>();
    TextView minuteTextview;

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
        graphInitSetting();

        BarChartGraph(labelList, valueList);
        barChart.setTouchEnabled(false); // 확대불가
        barChart.getAxisRight().setAxisMaxValue(100);
        barChart.getAxisLeft().setAxisMaxValue(100);

        /**
         * 아래 코드는 건들지 마시오.
         * 레이아웃을 다시 그려주는 코드 입니다.
         */
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.flags &= ~WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);

        /**
         * 이 아래로 코드를 작성해주세요.
         */



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

    public void graphInitSetting() {
        labelList.add(1);
        labelList.add(2);
        labelList.add(3);
        labelList.add(4);
        labelList.add(5);
        labelList.add(6);
        labelList.add(7);
        labelList.add(8);
        labelList.add(9);
        labelList.add(10);
        labelList.add(11);
        labelList.add(12);

        valueList.add(10);
        valueList.add(20);
        valueList.add(30);
        valueList.add(40);
        valueList.add(100);
        valueList.add(60);
        valueList.add(90);
        valueList.add(50);
        valueList.add(40);
        valueList.add(60);
        valueList.add(20);
        valueList.add(10);

        BarChartGraph(labelList, valueList);
        barChart.setTouchEnabled(false); // 확대불가
        barChart.getAxisRight().setAxisMaxValue(100);
        barChart.getAxisLeft().setAxisMaxValue(100);
    }

    private void BarChartGraph(ArrayList<Integer> labelList, ArrayList<Integer> valueList) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i=0; i< valueList.size(); i++) {
            entries.add(new BarEntry(i+1, (Integer) valueList.get(i)));
        }

        BarDataSet depenses = new BarDataSet(entries, "쓰레기 배출량 단위: kg");
        depenses.setAxisDependency(YAxis.AxisDependency.LEFT);
        barChart.setDescription(null);

        ArrayList<Integer> labels = new ArrayList<Integer>();
        for (int i=0; i < labelList.size(); i++) {
            labels.add(labelList.get(i));
        }

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
        data.setValueTextSize(14);
        data.setValueTypeface(Typeface.DEFAULT_BOLD);

        barChart.setData(data);
        barChart.animateXY(100, 100);
        barChart.invalidate();
    }
}