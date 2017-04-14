package com.example.dong.tracnghiemlaixe.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dong.tracnghiemlaixe.R;
import com.example.dong.tracnghiemlaixe.TestActivity;
import com.example.dong.tracnghiemlaixe.common.RippleView;

import java.util.List;

/**
 * Created by DONG on 27-Feb-17.
 */

public class RecyclerExamAdapter extends RecyclerView.Adapter<RecyclerExamAdapter.RecyclerViewHolder> {

    private List<Integer> list;
    private Context context;

    public RecyclerExamAdapter(List<Integer> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView btnExam;
        public ImageView imageView;
        public RippleView rippleView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);
            btnExam= (TextView)itemView.findViewById(R.id.btnExam);
            imageView= (ImageView) itemView.findViewById(R.id.image);
            rippleView= (RippleView) itemView.findViewById(R.id.more);
            rippleView.setOnClickListener(this);
        }
        @Override
        public void onClick(final View v) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    int id=list.get(getPosition()).intValue();
                    Intent intent=new Intent(v.getContext(), TestActivity.class);
                    intent.putExtra("exam",id);
                    v.getContext().startActivity(intent);
                }
            },200);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public RecyclerExamAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView =inflater.inflate(R.layout.custom_exam_layout,parent,false);
        return new RecyclerExamAdapter.RecyclerViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(RecyclerExamAdapter.RecyclerViewHolder holder, int position) {
        holder.btnExam.setText("Đề "+list.get(position).toString());
        holder.imageView.setVisibility(View.GONE);
    }
}
