package com.example.dong.tracnghiemlaixe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TableRow;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    TableRow trBook,trTest,trGuide,trSuggest;
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
        trSuggest= (TableRow) findViewById(R.id.trSuggest);

        trBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,BookActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.from_left);
            }
        });
        trTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ExamActivity.class);
                startActivity(intent);
            }
        });
        trGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setPackage("com.google.android.youtube");
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=PXwVbtxh8P4"));
                startActivity(intent);
            }
        });
        trSuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SuggestActivity.class);
                startActivity(intent);
            }
        });
    }
}
