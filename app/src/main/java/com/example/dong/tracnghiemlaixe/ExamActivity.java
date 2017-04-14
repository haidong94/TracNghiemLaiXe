package com.example.dong.tracnghiemlaixe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.dong.tracnghiemlaixe.adapter.RecyclerExamAdapter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by DONG on 27-Feb-17.
 */

public class ExamActivity extends AppCompatActivity {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerExamAdapter adapter;
    private GridLayoutManager lLayout;
    private Button btnSubmit;
    public ArrayList<Integer> list=new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_recycler_layout);
        addControl();
        addEvents();
    }

    private void addEvents() {
    }

    private void addControl() {
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);//mũi tên quay về

        btnSubmit= (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setVisibility(View.INVISIBLE);

        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        lLayout=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(lLayout);
        adapter=new RecyclerExamAdapter(list,this);
        recyclerView.setAdapter(adapter);
    }
}
