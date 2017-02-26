package com.example.deepdev_03.muvito.Utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.deepdev_03.muvito.R;

public class ItemClickSupportWithNoHeader
{
    private final RecyclerView recyclerView;
    private ItemClickSupportWithHeader.OnItemClickListener onItemClickListener;

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(v);
                onItemClickListener.onItemClicked(recyclerView, holder.getAdapterPosition(), v);
            }
        }
    };

    private RecyclerView.OnChildAttachStateChangeListener mAttachListener
            = new RecyclerView.OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(View view) {
            if (onItemClickListener != null) {
                view.setOnClickListener(mOnClickListener);
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {

        }
    };

    private ItemClickSupportWithNoHeader(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.recyclerView.setTag(R.id.item_click_support, this);
        this.recyclerView.addOnChildAttachStateChangeListener(mAttachListener);
    }

    public static ItemClickSupportWithNoHeader addTo(RecyclerView view) {
        ItemClickSupportWithNoHeader support = (ItemClickSupportWithNoHeader) view.getTag(R.id.item_click_support);
        if (support == null) {
            support = new ItemClickSupportWithNoHeader(view);
        }
        return support;
    }

    public ItemClickSupportWithNoHeader setOnItemClickListener(ItemClickSupportWithHeader.OnItemClickListener listener) {
        onItemClickListener = listener;
        return this;
    }

    private void detach(RecyclerView view) {
        view.removeOnChildAttachStateChangeListener(mAttachListener);
        view.setTag(R.id.item_click_support, null);
    }

    public interface OnItemClickListener {

        void onItemClicked(RecyclerView recyclerView, int position, View v);
    }
}
