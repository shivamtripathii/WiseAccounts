package com.wiselap.accounts.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wiselap.accounts.R;
import com.wiselap.accounts.model.ApprovalUserModel;

import java.util.ArrayList;

/**
 * @author Aprataksh Anand
 * Adapter for fetching list of approvals
 * */
public class ApprovalDefaultAdapter extends RecyclerView.Adapter<ApprovalDefaultAdapter.ApprovalViewHolder> {

    private ArrayList<ApprovalUserModel> arrayList;
    Context context;
    private OnApprovalListener onApprovalListener;

    public ApprovalDefaultAdapter(ArrayList<ApprovalUserModel> arrayList, Context context, OnApprovalListener onApprovalListener) {
        this.arrayList = arrayList;
        this.context = context;
        this.onApprovalListener = onApprovalListener;
    }

    @NonNull
    @Override
    public ApprovalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_expense_report, viewGroup, false);
        return new ApprovalViewHolder(v, onApprovalListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ApprovalViewHolder approvalViewHolder, int i) {
        approvalViewHolder.userName.setText(arrayList.get(i).getUserName());
        approvalViewHolder.expense.setText(String.valueOf(arrayList.get(i).getExpense()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ApprovalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView userName, expense;
        OnApprovalListener onApprovalListener;

        public ApprovalViewHolder(@NonNull View itemView, OnApprovalListener onApprovalListener) {
            super(itemView);
            this.onApprovalListener = onApprovalListener;
            userName = (TextView) itemView.findViewById(R.id.usename);
            expense = (TextView) itemView.findViewById(R.id.advance);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onApprovalListener.onApprovalClick(getAdapterPosition());
        }
    }

    public interface OnApprovalListener{
        void onApprovalClick(int position);
    }
}
