package com.Happy.happythrow;

import androidx.annotation.NonNull;
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
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class adduser extends AppCompatActivity {

    EditText numberinput,nameinput,banknumberinput,accinput;
    Button check,adduser;
    Spinner callbrand,bankBrand;
    String chosecallBrand,chosebankBrand,name,phonenum,banknum;
    int round1 = 0;

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);

        numberinput = findViewById(R.id.numberinput);
        check = findViewById(R.id.check1ok);
        nameinput = findViewById(R.id.NameInput);
        callbrand = findViewById(R.id.phone_spinner);
        bankBrand = findViewById(R.id.bankBrandspinner2);
        adduser = findViewById(R.id.adduserok);
        banknumberinput = findViewById(R.id.banknumber);
        accinput = findViewById(R.id.numbercheckinput);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        String[] callbranditem = {"KT","U+","SKT"};
        String[] bankitem = {"국민은행","기업은행","농협은행","하나은행","신한은행","SC제일은행","씨티은행","카카오뱅크","K뱅크"};
        ArrayAdapter<String> calladapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,callbranditem
        );
        ArrayAdapter<String> bankadapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,bankitem
        );
        calladapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        bankadapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        callbrand.setAdapter(calladapter);
        bankBrand.setAdapter(bankadapter);


        callbrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chosecallBrand = (callbranditem[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                chosecallBrand = "";
            }
        });

        bankBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chosebankBrand = (bankitem[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                chosebankBrand = "";
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    db.collection("Userdata").document(numberinput.getText().toString()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {


                            if (documentSnapshot.get("이름") != null) {
                                Toast.makeText(adduser.this, "이미가입되어있습니다.", Toast.LENGTH_SHORT).show();
                                savefile("id",numberinput.getText().toString());
                                Intent intent = new Intent(adduser.this,QR_main.class);
                                startActivity(intent);
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                finish();
                            }
                            else{
                                if(numberinput.getText().length() > 11 ||numberinput.getText().length() < 11 ){
                                    Toast.makeText(adduser.this,"올바른 번호로 입력해주세요.",Toast.LENGTH_SHORT).show();
                                }
                                else if(numberinput.getText().length() == 11 && !(nameinput.getText().toString().equals("")) && !(chosecallBrand.equals(""))&&!(nameinput.getText().toString().equals(""))){
                                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                                    imm.hideSoftInputFromWindow(numberinput.getWindowToken(), 0);
                                    Toast.makeText(adduser.this,"실명인증 완료.",Toast.LENGTH_SHORT).show();
                                    round1 = 1;
                                    name = nameinput.getText().toString();
                                    phonenum = numberinput.getText().toString();
                                }
                                else{
                                    Toast.makeText(adduser.this,"이름을 입력해주세요.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    });


            }
        });

        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!(banknumberinput.getText().toString().equals(""))&&!(accinput.getText().toString().equals("")) && !(chosebankBrand.equals(""))&& round1 == 1){
                    banknum = banknumberinput.getText().toString();
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(numberinput.getWindowToken(), 0);

                    Map<String, Object> userdata = new HashMap<>();
                    userdata.put("이름",name);
                    userdata.put("은행",chosebankBrand);
                    userdata.put("계좌번호",banknum);
                    userdata.put("전화번호",phonenum);

                    savefile("id.txt",phonenum);
                    db.collection("Userdata").document(phonenum).set(userdata).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(adduser.this,"회원가입 완료",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(adduser.this,QR_main.class);
                            startActivity(intent);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(adduser.this,"다시시도 해주세요.",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else if (round1 == 0){

                    Toast.makeText(adduser.this,"실명인증을 해주세요.",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(adduser.this,"계좌번호 또는 인증번호를 입력해주세요.",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public void savefile(String filename,String date){

        try {
            FileOutputStream fo = openFileOutput(filename,MODE_PRIVATE);
            DataOutputStream dos = new DataOutputStream(fo);
            dos.write(date.getBytes());
            dos.flush();
            dos.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }
}