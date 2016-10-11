package com.nutstudio.message.freeeye.component;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.nutstudio.message.freeeye.R;

import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private List<ListItemBean> list;
    private LayoutInflater mInflater;
    private Context context;
    private  ListItemBean bean;


    public MyListAdapter(Context context, List<ListItemBean> list) {
        mInflater = LayoutInflater.from(context);
        this.context=context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.title = (TextView) view.findViewById(R.id.title_tv);
        viewHolder.icon = (ImageView) view.findViewById(R.id.icon_im);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (position<list.size()){
             bean=list.get(position);
            holder.title.setText(bean.getTitle());
            Glide.with(context).load(bean.getIconUrl()).into(holder.icon);
            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickListener.onItemClick(holder.itemView, pos,bean.getShareImgUrl());
                    }
                });
            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position,String url);

        void onItemLongClick(View view, int position, String groupId);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickLitener) {
        this.mOnItemClickListener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
