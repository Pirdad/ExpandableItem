package com.pirdad.expandableitem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class TestActivity extends Activity {

    private Button btn_test;
    private Button btn_real;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);

        btn_test = (Button) findViewById(R.id.btn_test);
        btn_test.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TestActivity.this, SimpleActivity.class);
                startActivity(intent);
            }
        });
        btn_real = (Button) findViewById(R.id.btn_real);
        btn_real.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TestActivity.this, RealActivity.class);
                startActivity(intent);
            }
        });
    }
}
