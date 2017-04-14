package com.example.dong.tracnghiemlaixe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by DONG on 14-Apr-17.
 */

public class SuggestActivity extends AppCompatActivity {
    TextView txtSuggest1,txtSuggest2,txtSuggest3,txtSuggest4,txtSuggest5,txtSuggest6,
            txtSuggest1a,txtSuggest2a,txtSuggest3a,txtSuggest4a,txtSuggest5a,txtSuggest6a;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggest_layout);

        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtSuggest1= (TextView) findViewById(R.id.txtSuggest1);
        txtSuggest1a= (TextView) findViewById(R.id.txtSuggest1a);
        txtSuggest2= (TextView) findViewById(R.id.txtSuggest2);
        txtSuggest2a= (TextView) findViewById(R.id.txtSuggest2a);
        txtSuggest3= (TextView) findViewById(R.id.txtSuggest3);
        txtSuggest3a= (TextView) findViewById(R.id.txtSuggest3a);
        txtSuggest4= (TextView) findViewById(R.id.txtSuggest4);
        txtSuggest4a= (TextView) findViewById(R.id.txtSuggest4a);
        txtSuggest5= (TextView) findViewById(R.id.txtSuggest5);
        txtSuggest5a= (TextView) findViewById(R.id.txtSuggest5a);
        txtSuggest6= (TextView) findViewById(R.id.txtSuggest6);
        txtSuggest6a= (TextView) findViewById(R.id.txtSuggest6a);

        txtSuggest1.setText(getResources().getString(R.string.suggest1));
        txtSuggest1a.setText(getResources().getString(R.string.suggest1a));

        txtSuggest2.setText(getResources().getString(R.string.suggest2));
        txtSuggest2a.setText(getResources().getString(R.string.suggest2a));

        txtSuggest3.setText(getResources().getString(R.string.suggest3));
        txtSuggest3a.setText(getResources().getString(R.string.suggest3a));

        txtSuggest4.setText(getResources().getString(R.string.suggest4));
        txtSuggest4a.setText(getResources().getString(R.string.suggest4a));

        txtSuggest5.setText(getResources().getString(R.string.suggest5));
        txtSuggest5a.setText(getResources().getString(R.string.suggest5a));

        txtSuggest6.setText(getResources().getString(R.string.suggest6));
        txtSuggest6a.setText(getResources().getString(R.string.suggest6a));



        //quay về activity trước
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
