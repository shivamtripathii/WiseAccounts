package com.wiselap.accounts.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wiselap.accounts.R;

public class FundTransferAdapter extends RecyclerView.Adapter<FundTransferAdapter.FundHolder> {

    Context context;
    String[] items;
    int[] images;
    OnFundItemViewCLickListener onFundItemViewCLickListener;

    public FundTransferAdapter(Context context, String[] items, int[] images, OnFundItemViewCLickListener onFundItemViewCLickListener) {
        this.context = context;
        this.items = items;
        this.images = images;
        this.onFundItemViewCLickListener = onFundItemViewCLickListener;
    }

    @NonNull
    @Override
    public FundHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.fund_item, viewGroup, false);
        return new FundHolder(view,onFundItemViewCLickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FundHolder fundHolder, int i) {
        fundHolder.imageView.setImageResource(images[i]);
        fundHolder.textView.setText(items[i]);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public class FundHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        OnFundItemViewCLickListener onFundItemViewCLickListener;
        TextView textView;
        ImageView imageView;

        public FundHolder(@NonNull View itemView, OnFundItemViewCLickListener onFundItemViewCLickListener) {
            super(itemView);
            this.onFundItemViewCLickListener = onFundItemViewCLickListener;
            this.textView = itemView.findViewById(R.id.fundtextView);;
            this.imageView = itemView.findViewById(R.id.fundimageView);;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onFundItemViewCLickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnFundItemViewCLickListener {
        void onItemClick(int postion);
    }
}
