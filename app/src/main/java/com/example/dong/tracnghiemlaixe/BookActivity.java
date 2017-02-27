package com.example.dong.tracnghiemlaixe;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.dong.tracnghiemlaixe.adapter.RecyclerQuestionAdapter;
import com.example.dong.tracnghiemlaixe.database.DatabaseHelper;
import com.example.dong.tracnghiemlaixe.model.Items;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by DONG on 24-Feb-17.
 */

public class BookActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerQuestionAdapter adapter;
    private LinearLayoutManager lLayout;
    private DatabaseHelper mDBHelper;
    public ArrayList<Items> listItem=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_book_item);

        addControl();
        addEvents();
    }

    private void addEvents() {
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
        listItem= mDBHelper.getListItems();
        adapter=new RecyclerQuestionAdapter(listItem,this);
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




    }

    private void addControl() {
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
