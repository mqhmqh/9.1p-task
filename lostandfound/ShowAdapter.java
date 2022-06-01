package com.example.lostandfound;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.MyViewHolder> {


    private OnItemClickListener mOnItemClickListener;
    private List<ItemBean> iblSA1;
    private Context context;

    private DatabaseHelper dbHelper;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        int index = position;
        holder.tvDA1.setText(iblSA1.get(position).description);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view,index);
            }
        });

    }

    @Override
    public int getItemCount() {
        return iblSA1.size();
    }


    public interface OnItemClickListener {
        void onItemClick(View view,int position);
    }

    public void addItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tvDA1;
        public LinearLayout layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvDA1 = itemView.findViewById(R.id.tvDA1);
            layout = itemView.findViewById(R.id.linear);
        }
    }

    public ShowAdapter(List<ItemBean> iblSA1, Context context){
        this.iblSA1 = iblSA1;
        this.context = context;

    }


}
