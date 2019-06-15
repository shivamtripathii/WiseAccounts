package com.wiselap.accounts.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wiselap.accounts.R;
import com.wiselap.accounts.expense.ExpensePackage.ExpenseReturnModel;
import com.wiselap.accounts.expense.ExpensePackage.ExpensesContract;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseHolder> {
    Context context;
    ArrayList<ExpenseReturnModel> arrayList;

    public ExpenseAdapter(Context context, ArrayList<ExpenseReturnModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public ExpenseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.expense_item,viewGroup,false);
        return new ExpenseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseHolder expenseHolder, int i) {
            ExpenseReturnModel expenseModel=arrayList.get(i);
            expenseHolder.expensename.setText(expenseModel.getExpense_name());
            expenseHolder.expenseamount.setText(String.valueOf(expenseModel.getExpense_amount()));
            expenseHolder.expensetype.setText(expenseModel.getRemarks());
            expenseHolder.expensedate.setText(expenseModel.getDate());
            expenseHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ExpensesContract.View)context).expenseEdit(v, i);
                }
            });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ExpenseHolder extends RecyclerView.ViewHolder{
        TextView expensename,expensetype,expenseamount,expensedate;
        public ExpenseHolder(@NonNull View itemView) {

            super(itemView);
            expensename=itemView.findViewById(R.id.exp_name);
            expensetype=itemView.findViewById(R.id.exp_type);
            expenseamount=itemView.findViewById(R.id.amount);
            expensedate=itemView.findViewById(R.id.DateText);
        }
    }
}
