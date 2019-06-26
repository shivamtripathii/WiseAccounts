package com.wiselap.accounts.Select_Entity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.wiselap.accounts.Office.OfficeActivity;
import com.wiselap.accounts.Personal.PersonalActivity;
import com.wiselap.accounts.R;
 import com.wiselap.accounts.Select_Account.SelectAccountActivity;
import com.wiselap.accounts.SignIn.LoginActivity;
import com.wiselap.accounts.adapters.SelectEntityAdapter;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.databinding.ActivitySelectEntityBinding;
import com.wiselap.accounts.model.Item;
import com.wiselap.accounts.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Aprataksh Anand
 *
 * Activity for showing the list of accounts
 *
 * item_list : holds the list of entities from the server
 * ENTITY_OFFICE : onClick for Office Entity
 * ENTITY_PERSONAL : onClick for Personal Entity
 *
 * */

public class SelectEntityActivity extends BaseActivity implements SelectEntityContract.SEView, SelectEntityAdapter.OnEntityListener{

    final int ENTITY_OFFICE = 0;
    final int ENTITY_PERSONAL = 1;

    @Inject
    SelectEntityPresenter<SelectEntityContract.SEView> mPresenter;

    @Inject
    PreferenceUtils preferenceUtils;
    GoogleSignInClient mGoogleSignInClient;

    SelectEntityAdapter adapter;
    List<Item> item_list = new ArrayList<>();
    RecyclerView recyclerView;
    ActivitySelectEntityBinding select_binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        select_binding = DataBindingUtil.setContentView(this, R.layout.activity_select_entity);
        select_binding.toolbar.title.setText(getString(R.string.Select_Entity));
        select_binding.toolbar.addBtn.setVisibility(View.GONE);

        select_binding.toolbar.editBtn.setVisibility(View.GONE);
        select_binding.toolbar.delBtn.setImageResource(R.drawable.exit);
        mPresenter.onAttach(this);

        //Sets the Item List for Select Entity
        //setItem_list();

        //Sets the Recycler View
        setRecyclerView();

        //Sets the adapter for the Recycler View
        mPresenter.setAdapter();
    }

    /**
     * onClick() - manages the buttons
     * */
    public void onClick(View view){
        super.onClick(view);
        switch (view.getId()){
            case R.id.backBtn:
                showMessage(getString(R.string.Account_not_created));
                finish();
                break;
            case R.id.del_btn:
                AlertBox();
                break;
        }
    }

    private void manageOffice(){
        Intent intent = new Intent(this, OfficeActivity.class);
        intent.putExtra(AppConstants.Operation, AppConstants.ADD);
        startActivity(intent);
    }

    private void managePersonal() {
        Intent intent = new Intent(this, PersonalActivity.class);
        intent.putExtra(AppConstants.Operation, AppConstants.ADD);
        startActivity(intent);
    }


    /**
     * setting the Recycler View adapter (item decoration)
     * */
    private void setRecyclerView(){
        recyclerView = select_binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*DividerItemDecoration hDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable hDivider = ContextCompat.getDrawable(this, R.drawable.line_divider);
        hDecoration.setDrawable(hDivider);
        recyclerView.addItemDecoration(hDecoration);*/
    }

    @Override
    public void showMessage(String s) {
        super.showMessage(s);
    }

    @Override
    public void createAdapter(List<Item> itemList) {
        this.item_list = itemList;
        adapter = new SelectEntityAdapter(item_list,this, this);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Manage the click on a specific entity*/
    @Override
    public void onEntityClick(int position) {
        switch (position){
            case ENTITY_OFFICE:
                manageOffice();
                break;
            case ENTITY_PERSONAL:
                managePersonal();
                break;
        }
    }

    private void AlertBox() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Google Logout").setMessage("Do you want to Logout?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();

                mGoogleSignInClient = GoogleSignIn.getClient(SelectEntityActivity.this, gso);
                mGoogleSignInClient.signOut();
                preferenceUtils.clearAll();
                startActivity(new Intent(SelectEntityActivity.this, LoginActivity.class));
                finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }
}
