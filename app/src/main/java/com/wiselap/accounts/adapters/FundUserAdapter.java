package com.wiselap.accounts.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wiselap.accounts.FundUsers.FundUsersReturnModel;
import com.wiselap.accounts.R;
import com.wiselap.accounts.adapters.FundUserAdapter.FundUserHolder;

import java.util.List;

public class FundUserAdapter extends RecyclerView.Adapter<FundUserAdapter.FundUserHolder> {

    List<FundUsersReturnModel> arrayList;
    Context context;
    OnItemViewCLickListener onItemViewCLickListener;
    public FundUserAdapter(List<FundUsersReturnModel> arrayList, Context context, OnItemViewCLickListener onItemViewCLickListener) {
        this.arrayList = arrayList;
        this.context = context;
        this.onItemViewCLickListener=onItemViewCLickListener;

    }


    @NonNull
    @Override
    public FundUserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.funduser_item, viewGroup, false);
        return new FundUserHolder(view,onItemViewCLickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FundUserHolder fundUserHolder, int i) {

        FundUsersReturnModel model=arrayList.get(i);
        fundUserHolder.username.setText(model.getShopAgentName());
        fundUserHolder.advance.setText(String.valueOf(model.getAdvance()));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class FundUserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView username;
        TextView advance;
        OnItemViewCLickListener onItemViewCLickListener;
            public FundUserHolder(@NonNull View itemView,OnItemViewCLickListener onItemViewCLickListener) {
                super(itemView);
                username = itemView.findViewById(R.id.usename);
                advance = itemView.findViewById(R.id.advance);
                this.onItemViewCLickListener=onItemViewCLickListener;
                itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemViewCLickListener.onItemClick(getAdapterPosition());
        }
    }
    public interface OnItemViewCLickListener {
        void onItemClick(int postion);
    }
}
