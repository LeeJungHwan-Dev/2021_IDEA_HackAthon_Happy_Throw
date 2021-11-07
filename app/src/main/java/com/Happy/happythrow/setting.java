package com.Happy.happythrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class setting extends AppCompatActivity {

    ImageButton gotrash,gochart,gomybin;
    Button reset,rebank,add;
    Spinner bank;
    String bankname;
    String phone,username,userphone,userbanknum,userbank;
    EditText banknumber,accnum;
    LinearLayout readdbank;
    TextView infousername,infophonenum,infobanknum,infobankname;

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        gotrash = findViewById(R.id.Go_Trash_button);
        gochart = findViewById(R.id.Go_chart_button);
        gomybin = findViewById(R.id.Go_MyTrash_button);
        reset = findViewById(R.id.Reset);
        rebank = findViewById(R.id.ReEditbank);
        bank = findViewById(R.id.bankBrandspinner2);
        add = findViewById(R.id.adduserok);
        banknumber = findViewById(R.id.banknumber);
        accnum = findViewById(R.id.numbercheckinput);
        readdbank = findViewById(R.id.readdbanks);
        infousername = findViewById(R.id.infousername);
        infophonenum = findViewById(R.id.infophonenum);
        infobanknum = findViewById(R.id.infobanknum);
        infobankname = findViewById(R.id.infobankname);

        phone = readmemo("id.txt");

        ref();

        String[] bankitem = {"국민은행", "기업은행", "농협은행", "하나은행", "신한은행", "SC제일은행", "씨티은행", "카카오뱅크", "K뱅크"};
        ArrayAdapter<String> bankadapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, bankitem
        );
        bank.setAdapter(bankadapter);

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

        phone = readmemo("id.txt");

        bank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bankname = (bankitem[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                bankname = "";
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(banknumber.getText().toString().equals("")) && !(accnum.getText().toString().equals(""))) {

                    db.collection("Userdata").document(phone).update("은행",bankname);
                    db.collection("Userdata").document(phone).update("계좌번호",banknumber.getText().toString());

                    Toast.makeText(setting.this,"변경 완료",Toast.LENGTH_SHORT).show();

                    rebank.setVisibility(View.VISIBLE);
                    reset.setVisibility(View.VISIBLE);
                    readdbank.setVisibility(View.GONE);
                    ref();

                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(accnum.getWindowToken(), 0);
                }
                else{
                    Toast.makeText(setting.this,"내용을 입력해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });

        gotrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 이 코드는 인텐트를 활용하여 startActivity로 인텐트를 실행한다.
                 * 실행된 인텐트는 setting.this 에서 QR_main.class로 이동한다.
                 *  overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                 *  으로 페이드인 , 페이드 아웃 효과를 줌
                 *  finish()로 액티비티 종료(해당창 종료)
                 * */

                Intent intent = new Intent(setting.this, QR_main.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });

        gochart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 이 코드는 인텐트를 활용하여 startActivity로 인텐트를 실행한다.
                 * 실행된 인텐트는 setting.this 에서 trash_chart.class로 이동한다.
                 *  overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                 *  으로 페이드인 , 페이드 아웃 효과를 줌
                 *  finish()로 액티비티 종료(해당창 종료)
                 * */

                Intent intent = new Intent(setting.this, QR_main.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });

        gomybin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 이 코드는 인텐트를 활용하여 startActivity로 인텐트를 실행한다.
                 * 실행된 인텐트는 setting.this 에서 my_trash.class로 이동한다.
                 *  overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                 *  으로 페이드인 , 페이드 아웃 효과를 줌
                 *  finish()로 액티비티 종료(해당창 종료)
                 * */

                Intent intent = new Intent(setting.this, my_trash.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });


        rebank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reset.setVisibility(View.GONE);
                rebank.setVisibility(v.GONE);
                readdbank.setVisibility(View.VISIBLE);

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String path = getFilesDir().toString();
                File file = new File(path);
                File[] files = file.listFiles();
                int a = files.length;
                for (int i = 0; i < a; i++) {
                    if(files[i].toString().length() < 45){

                    }
                    else {
                        String path2 = files[i].toString();
                        File file1 = new File(path2);
                        if (file1.exists()) {
                            file1.delete();
                        }
                    }
                }
                Toast.makeText(setting.this,"초기화 완료",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public String readmemo(String fileName) {

        try {
            // 파일에서 읽은 데이터를 저장하기 위해서 만든 변수
            StringBuffer data = new StringBuffer();
            FileInputStream fs = openFileInput(fileName);//파일명
            BufferedReader buffer = new BufferedReader
                    (new InputStreamReader(fs));
            String str = buffer.readLine(); // 파일에서 한줄을 읽어옴
            if (str != null) {
                while (str != null) {
                    data.append(str);
                    str = buffer.readLine();
                }
                buffer.close();
                return data.toString();
            }
        } catch (Exception e) {

        }
        return null;
    }

    public void ref(){
        try {
            db.collection("Userdata").document(phone).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    username = documentSnapshot.get("이름").toString();
                    userphone = documentSnapshot.get("전화번호").toString();
                    userbanknum = documentSnapshot.get("계좌번호").toString();
                    userbank = documentSnapshot.get("은행").toString();
                    StringBuffer sb = new StringBuffer();
                    sb.append(userphone);
                    sb.insert(3,"-");
                    sb.insert(8,"-");
                    infousername.setText("이름 : "+username);
                    infophonenum.setText("전화번호 : "+sb);
                    infobanknum.setText("계좌번호 : "+userbanknum);
                    infobankname.setText("은행 : "+userbank);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}