package com.Happy.happythrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class my_trash extends AppCompatActivity {

    /**
     * 이곳에
     * (사용할 아이템) 변수 이름;
     * 형태로 지정한 다음 아래 참고.
     */

    ImageButton Gotrash,Gochart,Gosetting;
    public class trashitem {
        String name;
        String howfull;
        int fullnum;

        public trashitem(String name, String howfull, int fullnum) {
            this.name = name;
            this.howfull = howfull;
            this.fullnum = fullnum;
        }

        public String getName(){
            return name;
        }

        public String gethowfull(){
            return howfull;
        }

        public int getfullnum(){
            return fullnum;
        }

        public void setName(String name){
            this.name = name;
        }

        public void sethowfull(String howfull){
            this.howfull = howfull;
        }

        public void setfullnum(int fullnum){
            this.fullnum = fullnum;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trash);

        /**
         * xml과 자바 코드를 연결 하는 작업니다.
         * 아래와 같이 위에 선언한 변수를 findViewById(R.id.~~~~) 식으로 xml과 연결
         */

        Gotrash = findViewById(R.id.Go_Trash_button);
        Gochart = findViewById(R.id.Go_chart_button);
        Gosetting = findViewById(R.id.Go_setting_button);
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

        Gotrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 이 코드는 인텐트를 활용하여 startActivity로 인텐트를 실행한다.
                 * 실행된 인텐트는 my_trash.this 에서 QR_main.class로 이동한다.
                 *  overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                 *  으로 페이드인 , 페이드 아웃 효과를 줌
                 *  finish()로 액티비티 종료(해당창 종료)
                 * */

                Intent intent = new Intent(my_trash.this,QR_main.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });

        Gochart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 이 코드는 인텐트를 활용하여 startActivity로 인텐트를 실행한다.
                 * 실행된 인텐트는 my_trash.this 에서 trash_chart.class로 이동한다.
                 *  overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                 *  으로 페이드인 , 페이드 아웃 효과를 줌
                 *  finish()로 액티비티 종료(해당창 종료)
                 * */

                Intent intent = new Intent(my_trash.this,trash_chart.class);
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
                 * 실행된 인텐트는 my_trash.this 에서 setting.class로 이동한다.
                 *  overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                 *  으로 페이드인 , 페이드 아웃 효과를 줌
                 *  finish()로 액티비티 종료(해당창 종료)
                 * */

                Intent intent = new Intent(my_trash.this,setting.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });

    }
}