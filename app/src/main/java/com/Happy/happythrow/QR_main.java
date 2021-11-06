package com.Happy.happythrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;


public class QR_main extends AppCompatActivity {
    String name;

    /**
     * 이곳에
     * (사용할 아이템) 변수 이름;
     * 형태로 지정한 다음 아래 참고.
     */

    Button QR_Shot, open, close;
    ImageButton GoMyBin, GoMyChart, GoSetting;
    ImageView trashbin;
    View v;

    private IntentIntegrator qrScan;

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    View QR_Shot1;
    View Open;
    View Close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_main);
        /**
         * xml과 자바 코드를 연결 하는 작업니다.
         * 아래와 같이 위에 선언한 변수를 findViewById(R.id.~~~~) 식으로 xml과 연결
         */

        QR_Shot = findViewById(R.id.QR_shot);
        open = findViewById(R.id.TrashOpen);
        close = findViewById(R.id.TrashClose);
        trashbin = findViewById(R.id.trashbin);
        GoMyBin = findViewById(R.id.Go_MyTrash_button);
        GoMyChart = findViewById(R.id.Go_chart_button);
        GoSetting = findViewById(R.id.Go_setting_button);

        qrScan = new IntentIntegrator(this);

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

        QR_Shot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 이곳에 QR코드 촬영 버튼을 누르면 작동할 내용 작성.
                 */
                qrScan.setPrompt("Scanning.... ");//QRcode scan 아래에 문구 띄우기
                qrScan.initiateScan();//QR 코드 scan 실행

                QR_Shot1=view;
                /**
                 * QR 촬영이 성공하면 아래 오픈 , 닫기 버튼을 VISIBLE 처리 할 것.
                 * QR 촬영 버튼은 GONE 처리 할 것.
                 * open.setVisibility(View.VISIBLE);
                 * close.setVisibility(View.VISIBLE);
                 * QR_Shot.setVisibility(View.GONE);
                 * trashbin.setVisibility(View.VISIBLE);
                 */
            }
        });

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 이곳에 open 이벤트 작성 할 것.
                 * 서버에 값을 전송 또는 수정 등등
                 */
                Open=view;
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 이곳에 close 이벤트 작성 할 것.
                 * 서버에 값을 전송 또는 수정 등등
                 */
                Close=view;
            }
        });

        GoMyChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * 이 코드는 인텐트를 활용하여 startActivity로 인텐트를 실행했다.
                 * 실행된 인텐트는 QR_main.this 에서 trash_chart.class로 이동한다.
                 *  overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                 *  으로 페이드인 , 페이드 아웃 효과를 줌
                 *  finish()로 액티비티 종료(해당창 종료)
                 * */
                Intent intent = new Intent(QR_main.this, trash_chart.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });

        GoMyBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 이 코드는 인텐트를 활용하여 startActivity로 인텐트를 실행했다.
                 * 실행된 인텐트는 QR_main.this 에서 my_trash.class로 이동한다.
                 *  overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                 *  으로 페이드인 , 페이드 아웃 효과를 줌
                 *  finish()로 액티비티 종료(해당창 종료)
                 * */
                Intent intent = new Intent(QR_main.this, my_trash.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });



        GoSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 이 코드는 인텐트를 활용하여 startActivity로 인텐트를 실행했다.
                 * 실행된 인텐트는 QR_main.this 에서 setting.class로 이동한다.
                 *  overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                 *  으로 페이드인 , 페이드 아웃 효과를 줌
                 *  finish()로 액티비티 종료(해당창 종료)
                 * */
                Intent intent = new Intent(QR_main.this, setting.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(QR_main.this, "취소!", Toast.LENGTH_SHORT).show();
            }
            else {
                //qrcode 결과가 있으면
                Toast.makeText(QR_main.this, "스캔완료!", Toast.LENGTH_SHORT).show();
                QR_Shot.setVisibility(QR_Shot1.GONE);
                trashbin.setVisibility(trashbin.VISIBLE);
                open.setVisibility(Open.VISIBLE);
                close.setVisibility(close.VISIBLE);

                name=result.getContents().toString();
                String trash ="trash"+ UUID.randomUUID().toString();
                savefile(trash,name);

               try {
                   open.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           db.collection("trash").document(result.getContents().toString()).update("열림체크","1");
                       }

                   });

                   close.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           db.collection("trash").document(result.getContents().toString()).update("열림체크","0");

                       }
                   });

               } catch (Exception e) {
                   e.printStackTrace();
               }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



    public void savefile(String filename,String date){

            try {
                FileOutputStream fo = openFileOutput(filename+".txt", MODE_PRIVATE);
                DataOutputStream dos = new DataOutputStream(fo);
                dos.write(date.getBytes());
                dos.flush();
                dos.close();
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            }
    }
}