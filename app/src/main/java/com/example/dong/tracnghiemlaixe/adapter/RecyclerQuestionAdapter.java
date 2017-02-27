package com.example.dong.tracnghiemlaixe.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dong.tracnghiemlaixe.R;
import com.example.dong.tracnghiemlaixe.model.Items;

import java.util.List;

/**
 * Created by DONG on 25-Feb-17.
 */

public class RecyclerQuestionAdapter extends RecyclerView.Adapter<RecyclerQuestionAdapter.RecyclerViewHolder> {

    private List<Items> itemsList;
    private Context context;

    public RecyclerQuestionAdapter(List<Items> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CheckBox ckbOption1,ckbOption2,ckbOption3,ckbOption4;
        public TextView txtQuestion;
        public ImageView image;
        public Button btnAnswer;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);


            txtQuestion= (TextView) itemView.findViewById(R.id.txtQuestion);
            ckbOption1= (CheckBox) itemView.findViewById(R.id.ckbOption1);
            ckbOption2= (CheckBox) itemView.findViewById(R.id.ckbOption2);
            ckbOption3= (CheckBox) itemView.findViewById(R.id.ckbOption3);
            ckbOption4= (CheckBox) itemView.findViewById(R.id.ckbOption4);
            image= (ImageView) itemView.findViewById(R.id.image);
            btnAnswer= (Button) itemView.findViewById(R.id.btnAnswer);
            btnAnswer.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            ckbOption1.setChecked(false);
            ckbOption2.setChecked(false);
            ckbOption3.setChecked(false);
            ckbOption4.setChecked(false);

            int id = Resources.getSystem().getIdentifier("btn_check_holo_light", "drawable", "android");
            for (int i=0;i<itemsList.get(getPosition()).getAnswer().length();i++)
            {
                switch (itemsList.get(getPosition()).getAnswer().charAt(i)){
                    case '1':
                        ckbOption1.setChecked(true);
                        ckbOption1.setButtonDrawable(id);
                        break;
                    case '2':
                        ckbOption2.setChecked(true);
                        ckbOption2.setButtonDrawable(id);
                        break;
                    case '3':
                        ckbOption3.setChecked(true);
                        ckbOption3.setButtonDrawable(id);
                        break;
                    case '4':
                        ckbOption4.setChecked(true);
                        ckbOption4.setButtonDrawable(id);
                        break;
                }

            }
        }
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView =inflater.inflate(R.layout.custom_cyclerview,parent,false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.txtQuestion.setText("  \u25BA Câu "+itemsList.get(position).getId()+": "+itemsList.get(position).getQuestion());
        holder.ckbOption1.setText(""+itemsList.get(position).getOption1());
        holder.ckbOption2.setText(""+itemsList.get(position).getOption2());
//        holder.ckbOption1.refreshDrawableState();
//        holder.ckbOption2.refreshDrawableState();
//        holder.ckbOption3.refreshDrawableState();
//        holder.ckbOption4.
//        holder.ckbOption1.setChecked(false);
//        holder.ckbOption2.setChecked(false);
//        holder.ckbOption3.setChecked(false);
//        holder.ckbOption4.setChecked(false);



        if(itemsList.get(position).getIllustrationId()==0)
            holder.image.setVisibility(View.GONE);
        else {
            holder.image.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(Uri.parse("file:///android_asset/images/i" + itemsList.get(position).getIllustrationId() + ".jpg"))
                    .into(holder.image);
        }


        if(itemsList.get(position).getOption3()!=null) {
            holder.ckbOption3.setVisibility(View.VISIBLE);
            holder.ckbOption3.setText("" + itemsList.get(position).getOption3());
        }
        else
            holder.ckbOption3.setVisibility(View.INVISIBLE);

        if(itemsList.get(position).getOption4()!=null) {
            holder.ckbOption4.setVisibility(View.VISIBLE);
            holder.ckbOption4.setText("" + itemsList.get(position).getOption4());
        }
        else
            holder.ckbOption4.setVisibility(View.INVISIBLE);

    }


}
