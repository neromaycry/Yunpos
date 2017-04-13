package com.joeykwok.yunposv1.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joeykwok.yunposv1.R;
import com.joeykwok.yunposv1.common.Utils;
import com.joeykwok.yunposv1.custom.MyTextView;
import com.joeykwok.yunposv1.responsebean.rpSkuBean;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gjwlg on 2017/3/31.
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private Context mContext;
    private List<rpSkuBean.GoodsDetailBean> mList;

    private List<Boolean> isClicks; //控件是否被点击,默认为false，如果被点击，改变值，控件根据值改变自身颜色

    public static interface onRecyclerViewItemClickListener {
        void onItemClick(View view);

        void onItemLongClick(View view);

        void onItemClick(View view, LinearLayout layout, int position);
    }

    public interface ListUpdateListener {
        void onListUpdated(List<rpSkuBean.GoodsDetailBean> list);
    }

    private onRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(onRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setIsClicks() {
        isClicks.clear();
        for (int i = 0; i < mList.size(); i++) {
            if (i == mList.size() - 1) {
                isClicks.add(true);
            }
            isClicks.add(false);
        }
    }

    //适配器初始化
    public OrderListAdapter(Context mContext, List<rpSkuBean.GoodsDetailBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        isClicks = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            if (i == mList.size() - 1) {
                isClicks.add(true);
            }
            isClicks.add(false);
        }
    }


    @Override
    public OrderListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_order_list, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Log.d("Joey", "" + holder.tvItemName);
        double num = mList.get(position).getNum();
        double discount =mList.get(position).getDiscount();
        double price = mList.get(position).getPrice();
        double subtotal = Utils.sub(Utils.mul(num, price), discount);
        DecimalFormat df = new DecimalFormat("#0.00");
        holder.tvItemName.setText(mList.get(position).getGoods_name());
        holder.tvBarcode.setText(mList.get(position).getBarcode());
        holder.tvNum.setText(df.format(num));
        holder.tvSpec.setText(mList.get(position).getSpec());
        holder.tvPrice.setText(df.format(price));
        holder.tvDiscount.setText(df.format(discount));
        holder.tvSubtotal.setText(df.format(subtotal));

        holder.itemView.setTag(holder.layout);
        if (isClicks.size() > 0) {
            if (isClicks.get(position)) {
                holder.layout.setBackgroundColor(Color.parseColor("#b3e5fc"));
            } else {
                holder.layout.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < isClicks.size(); i++) {
                        isClicks.set(i, false);
                    }
                    isClicks.set(position, true);
                    notifyDataSetChanged();
                    mOnItemClickListener.onItemClick(holder.itemView, holder.layout, position);
                }
            });
        }

        ListUpdateListener listener = (ListUpdateListener) mContext;
        listener.onListUpdated(mList);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemLongClick(v);
        }
        return false;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        //        @BindView(R.id.tv_item_name)
        TextView tvItemName;
        //        @BindView(R.id.tv_barcode)
        MyTextView tvBarcode;

        MyTextView tvNum;

        MyTextView tvSpec;

        MyTextView tvPrice;

        MyTextView tvDiscount;

        MyTextView tvSubtotal;

        LinearLayout layout;

        public MyViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(itemView);
            tvItemName = (TextView) itemView.findViewById(R.id.tv_item_name);
            tvBarcode = (MyTextView) itemView.findViewById(R.id.tv_barcode);
            tvNum = (MyTextView) itemView.findViewById(R.id.tv_num);
            layout = (LinearLayout) itemView.findViewById(R.id.layout_item);
            tvSpec = (MyTextView) itemView.findViewById(R.id.tv_spec);
            tvPrice = (MyTextView) itemView.findViewById(R.id.tv_price);
            tvDiscount = (MyTextView) itemView.findViewById(R.id.tv_discount);
            tvSubtotal = (MyTextView) itemView.findViewById(R.id.tv_subtotal);
        }
    }


}
