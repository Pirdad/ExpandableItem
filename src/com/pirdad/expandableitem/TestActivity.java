package com.pirdad.expandableitem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class TestActivity extends Activity {

    private Button btn_simple_single;
    private Button btn_simple_list;
    private Button btn_real_test;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);

        btn_simple_single = (Button) findViewById(R.id.btn_simple_single);
        btn_simple_single.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TestActivity.this, SimpleSingleActivity.class);
                startActivity(intent);
            }
        });
        btn_simple_list = (Button) findViewById(R.id.btn_simple_list);
        btn_simple_list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TestActivity.this, SimpleListActivity.class);
                startActivity(intent);
            }
        });
        btn_real_test = (Button) findViewById(R.id.btn_real_test);
        btn_real_test.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TestActivity.this, RealActivity.class);
                startActivity(intent);
            }
        });
    }
}
