package com.example.dong.tracnghiemlaixe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TableRow;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    TableRow trBook,trTest,trGuide;
    ViewFlipper viewFlipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControl();
        addEvent();
    }

    private void addEvent() {
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.in_from_right));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.out_from_left));
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(4000);

    }

    private void addControl() {
        viewFlipper= (ViewFlipper) findViewById(R.id.vwFlipper);
        trBook= (TableRow) findViewById(R.id.trBook);
        trTest= (TableRow) findViewById(R.id.trTest);
        trGuide= (TableRow) findViewById(R.id.trGuide);
        trBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,BookActivity.class);
                startActivity(intent);
            }
        });
    }
}
