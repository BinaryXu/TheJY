package com.example.thejiyu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thejiyu.R;

import java.util.List;
import java.util.Map;

/**
 * Created by 峰 on 2016/6/15.
 */
public class SimpleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private LayoutInflater mInflater;
    private  Context mContext;
    private List mDatas;
    public SimpleAdapter(Context context,List data){
        this.mContext = context;
        this.mDatas = data;
        mInflater = LayoutInflater.from(context);
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = mInflater.inflate(R.layout.item_single_view, parent,
                    false);
            return new MyViewHolder(view);
//        } else if (viewType == TYPE_FOOTER) {
//            View view = mInflater.inflate(R.layout.item_foot, parent,
//                    false);
//            return new FootViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
//            holder.textView.setText(data.get(position));
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, position);
                    }
                });

//                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                    @Override
//                    public boolean onLongClick(View v) {
//                        int position = holder.getLayoutPosition();
//                        onItemClickListener.onItemLongClick(holder.itemView, position);
//                        return false;
//                    }
//                });
            }
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size() == 0 ? 0 : mDatas.size() + 1;
    }

}
class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tv;

    public MyViewHolder(View view) {
        super(view);
        tv = (TextView) view.findViewById(R.id.id_tv);
    }
}
class FootViewHolder extends RecyclerView.ViewHolder {

    public FootViewHolder(View view) {
        super(view);
    }
}