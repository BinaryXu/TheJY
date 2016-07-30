package com.example.thejiyu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    //上拉加载更多
    public static final int  PULLUP_LOAD_MORE=0;
    //正在加载中
    public static final int  LOADING_MORE=1;
    //上拉加载更多状态-默认为0
    private int load_more_status=0;
    //下拉刷新
    private static final int TYPE_ITEM = 0;
    //上拉刷新
    private static final int TYPE_FOOTER = 1;

    private MyViewHolder myViewHolder;
    private FootViewHolder footViewHolder;
    private LayoutInflater mInflater;
    private  Context mContext;
    private List<String> mDatas;
    public SimpleAdapter(Context context,List<String> data){
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
        if(viewType == TYPE_ITEM) {
            View view = mInflater.inflate(R.layout.item_single_view, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }else if(viewType == TYPE_FOOTER) {
            View view = mInflater.inflate(R.layout.item_single_view, parent, false);
            FootViewHolder footViewHolder = new FootViewHolder(view);
            return footViewHolder;
        }
        return null;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            myViewHolder = (MyViewHolder) holder;
            myViewHolder.tv.setText(mDatas.get(position));
            myViewHolder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(myViewHolder.tv, position);
                }
            });
            myViewHolder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(myViewHolder.tv, position);
                }
            });
        } else if (holder instanceof FootViewHolder) {
            footViewHolder = (FootViewHolder) holder;
            switch (load_more_status) {
                case PULLUP_LOAD_MORE:
                    footViewHolder.tv.setText("上拉加载更多...");
                    break;
                case LOADING_MORE:
                    footViewHolder.tv.setText("加载中...");
                    break;
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
//        return mDatas.size() == 0 ? 0 : mDatas.size() + 1;
        Log.d("test", "SimpleAdapter date.size:"+mDatas.size());
        return mDatas.size();
    }

    /**
     * //上拉加载更多
     * PULLUP_LOAD_MORE=0;
     *  //正在加载中
     * LOADING_MORE=1;
     * //加载完成已经没有更多数据了
     * NO_MORE_DATA=2;
     * @param status
     */
    public void changeMoreStatus(int status){
        load_more_status=status;
        notifyDataSetChanged();
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
    TextView tv;
    public FootViewHolder(View view) {
        super(view);
        tv = (TextView) view.findViewById(R.id.id_tv);
    }
}
