package com.example.dong.tracnghiemlaixe.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dong.tracnghiemlaixe.R;
import com.example.dong.tracnghiemlaixe.model.Items;

import java.util.List;

/**
 * Created by DONG on 07-Mar-17.
 */

public class RecyclerMyAnswerAdapter extends RecyclerView.Adapter<RecyclerMyAnswerAdapter.RecyclerViewHolder> {

    private List<Items> list;
    private Context context;

    public RecyclerMyAnswerAdapter(List<Items> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //  public Integer getItem(int position){
    //      return list.get(position);
    // }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView btnExam;
        public ImageView imageView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);
            btnExam= (TextView) itemView.findViewById(R.id.btnExam);
            imageView= (ImageView) itemView.findViewById(R.id.image);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
//            int id=list.get(getPosition()).intValue();
//            Intent intent=new Intent(v.getContext(), TestActivity.class);
//            intent.putExtra("exam",id);
//            v.getContext().startActivity(intent);



        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public RecyclerMyAnswerAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView =inflater.inflate(R.layout.custom_exam_layout,parent,false);
        return new RecyclerMyAnswerAdapter.RecyclerViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(RecyclerMyAnswerAdapter.RecyclerViewHolder holder, int position) {
        int a=list.indexOf(list.get(position))+1;
        holder.btnExam.setText("Câu "+a);
        if(list.get(position).getAnswer().replace(",","").equals(list.get(position).getMyAnswer()))
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.right));
        else
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.delete));
    }


}
