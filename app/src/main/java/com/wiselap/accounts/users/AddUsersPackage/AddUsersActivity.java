package com.wiselap.accounts.users.AddUsersPackage;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.wiselap.accounts.R;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.databinding.ActivityAddUsersBinding;
import com.wiselap.accounts.users.UsersPackage.UserReturnModel;
import com.wiselap.accounts.utils.PreferenceUtils;

import javax.inject.Inject;

public class AddUsersActivity extends BaseActivity implements AdapterView.OnItemSelectedListener,AddUserContract.view {
    ActivityAddUsersBinding activityAddUsersBinding;
    EditText username;
    Spinner profilename;
    Button next;
    UserReturnModel userReturnModel;
    ArrayAdapter<CharSequence> adapter;
    Long accountingUserId;
    Long accountingProfileId;

    @Inject
    PreferenceUtils preferenceUtils;
    @Inject
    AddUserPresenter<AddUserContract.view> presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddUsersBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_users);
        username = activityAddUsersBinding.usernameId;
        profilename = activityAddUsersBinding.profileId;
        next = activityAddUsersBinding.nextId;
        adapter();
        if (getIntent().getStringExtra(AppConstants.Operation).equals(AppConstants.EDIT)) {
            activityAddUsersBinding.include.title.setText("Edit Users");
            userReturnModel = (UserReturnModel) getIntent().getSerializableExtra("users");
            username.setText(userReturnModel.getUserName());
            profilename.setSelection(adapter.getPosition(userReturnModel.getUserProfile()));
            accountingUserId=userReturnModel.getAccountingUserId();
            accountingProfileId=userReturnModel.getAccountingProfileId();
            activityAddUsersBinding.userID.setText(userReturnModel.getUserId());

        } else if (getIntent().getStringExtra(AppConstants.Operation).equals(AppConstants.ADD)) {
            activityAddUsersBinding.include.title.setText("Add Users");
        }
        activityAddUsersBinding.include.addBtn.setVisibility(View.GONE);
        activityAddUsersBinding.include.editBtn.setVisibility(View.GONE);
        activityAddUsersBinding.include.delBtn.setVisibility(View.GONE);
        presenter.onAttach(this);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // data taking from add users
                if (isValid()) {
                    //startActivity(new Intent(AddUsersActivity.this,UsersActivity.class));
                    String user = username.getText().toString().trim();
                    String profile = profilename.getSelectedItem().toString().trim();
                    String userid = activityAddUsersBinding.userID.getText().toString().trim();
                    if (getIntent().getStringExtra(AppConstants.Operation).equals(AppConstants.EDIT)) {
                        presenter.updateUser(new UpdateUserMethodModel(accountingUserId,accountingProfileId,(long)0,user,userid,profile));
                    } else if (getIntent().getStringExtra(AppConstants.Operation).equals(AppConstants.ADD)) {
                        presenter.getMeta(new AddUserMethodModel(preferenceUtils.getAccountingProfile(),(long)0, user, profile, userid));
                    }

                } else
                    Toast.makeText(AddUsersActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });

        activityAddUsersBinding.include.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(AddUsersActivity.this,UsersActivity.class));
                finish();
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void adapter() {
        adapter = ArrayAdapter.createFromResource(this, R.array.profile, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        profilename.setAdapter(adapter);
        profilename.setOnItemSelectedListener(this);
    }

    private boolean isValid() {
        String checkUserName=username.getText().toString();
        if (!checkUserName.equals("")) {
            return true;
        } else
            return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String profile = parent.getItemAtPosition(position).toString();
        //Toast.makeText(this,""+profile,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this,"Error Select Profile",Toast.LENGTH_LONG).show();
    }



    @Override
    public void sendMeta(int meta) {
        if(meta==200)
        {
            showMessage("SUCCESS");
            finish();
        }
        else
            showMessage("Something went wrong");
    }

}
