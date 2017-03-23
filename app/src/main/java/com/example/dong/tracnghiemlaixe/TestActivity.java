package com.example.dong.tracnghiemlaixe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dong.tracnghiemlaixe.adapter.RecyclerTestAdapter;
import com.example.dong.tracnghiemlaixe.database.DatabaseHelper;
import com.example.dong.tracnghiemlaixe.model.Items;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by DONG on 28-Feb-17.
 */

public class TestActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerTestAdapter adapter;
    private LinearLayoutManager lLayout;
    private DatabaseHelper mDBHelper;
    public ArrayList<Items> listItem=null;
    Toolbar toolbar;
    Button btnSubmit;
    RelativeLayout relativeLayout;
    TextView minute,second;
    private static final String FORMAT = "%02d:%02d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_recycler_layout);

        addControl();
        addEvents();






    }

    private void addEvents() {
        final Intent intent=getIntent();
        int id=intent.getIntExtra("exam",10);

        mDBHelper=new DatabaseHelper(this);

        File database=getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(false==database.exists()){
            mDBHelper.getReadableDatabase();

            if(copyDatabase(this)){
                Toast.makeText(this,"Copied",Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this,"error",Toast.LENGTH_LONG).show();
        }
        listItem= mDBHelper.getList20Items(id);
        adapter=new RecyclerTestAdapter(listItem,this);
        recyclerView.setAdapter(adapter);

        //vuốt sang sẽ next 1 sang item tiếp theo
        LinearSnapHelper snapHelper = new LinearSnapHelper() {
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                int targetPos = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
                final View currentView = findSnapView(layoutManager);
                if(targetPos != RecyclerView.NO_POSITION && currentView != null){
                    int currentPostion = layoutManager.getPosition(currentView);
                    int first = ((LinearLayoutManager)layoutManager).findFirstVisibleItemPosition();
                    int last = ((LinearLayoutManager)layoutManager).findLastVisibleItemPosition();
                    currentPostion = targetPos < currentPostion ? last : (targetPos > currentPostion ? first : currentPostion);
                    targetPos = targetPos < currentPostion ? currentPostion - 1 : (targetPos > currentPostion ? currentPostion + 1 : currentPostion);
                }
                return targetPos;
            }
        };

        snapHelper.attachToRecyclerView(recyclerView);




        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=0;
                for(Items items:listItem)
                {
                    if(items.getAnswer().replace(",","").equals(items.getMyAnswer()))
                        a++;
                }
                Toast.makeText(TestActivity.this,"Bạn đúng "+a+" câu",Toast.LENGTH_SHORT).show();

                Intent intent1=new Intent(TestActivity.this,MyAnswerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("list", listItem);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });


        new CountDownTimer(1200000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                second.setText(""+String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                Intent intent1=new Intent(TestActivity.this,MyAnswerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("list", listItem);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        }.start();

    }

    private void addControl() {

        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        relativeLayout= (RelativeLayout) findViewById(R.id.rltLayout);
        relativeLayout.setVisibility(View.VISIBLE);
        minute= (TextView) findViewById(R.id.minute);
        second= (TextView) findViewById(R.id.second);


        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);//mũi tên quay về
        btnSubmit= (Button) findViewById(R.id.btnSubmit);

        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        lLayout=new LinearLayoutManager(this);

        lLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(lLayout);

    }

    private boolean copyDatabase(Context context){
        try{
            InputStream inputStream=context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName=DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream=new FileOutputStream(outFileName);
            byte[] buff=new byte[1024];
            int lenght=0;
            while ((lenght=inputStream.read(buff))>0){
                outputStream.write(buff,0,lenght);
            }

            outputStream.flush();
            outputStream.close();
            Log.v("Mai","DB copied");
            return  true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
