package com.wiselap.accounts.di.component;


import com.wiselap.accounts.ApplicationSetting.ApplicationSettingActivity;
import com.wiselap.accounts.Configuration.ConfigurationActivity;
import com.wiselap.accounts.ExpenseType.AddExpenseType.AddExpenseTypeActivity;
import com.wiselap.accounts.ExpenseType.AddExpenseType.ApiModule;
import com.wiselap.accounts.ExpenseType.ExpenseTypeActivity;
import com.wiselap.accounts.ExpenseType.ExpenseTypeModule;
import com.wiselap.accounts.FundTransfer.FundTransferActivity;
import com.wiselap.accounts.FundTransferMethod.FundMethodModule;
import com.wiselap.accounts.FundTransferMethod.FundTransferMethodActivity;
import com.wiselap.accounts.FundUsers.FundUsersActivity;
import com.wiselap.accounts.FundUsers.FundUsersModule;
import com.wiselap.accounts.Office.OfficeActivity;
import com.wiselap.accounts.Personal.PersonalActivity;
import com.wiselap.accounts.Personal.PersonalModule;
import com.wiselap.accounts.Report.Expense_Report.ExpenseReportDisplay.ExpenseReportDisplayActivity;
import com.wiselap.accounts.Report.Expense_Report.ExpenseReportSearch.ExpenseReportActivity;
import com.wiselap.accounts.Report.ReportActivity;
import com.wiselap.accounts.Report.Salary_Report.SalaryReportDisplay.SalaryReportDisplayActivity;
import com.wiselap.accounts.Report.Salary_Report.SalaryReportSearch.SalaryReportActivity;
import com.wiselap.accounts.Select_Account.SelectAccountActivity;
import com.wiselap.accounts.Select_Entity.SelectEntityActivity;
import com.wiselap.accounts.Select_Entity.SelectEntityModule;
import com.wiselap.accounts.SignIn.LoginActivity;
import com.wiselap.accounts.expense.AddExpensePackage.AddExpenseActivity;
import com.wiselap.accounts.expense.AddExpensePackage.AddExpenseModule;
import com.wiselap.accounts.expense.ExpensePackage.ExpenseModule;
import com.wiselap.accounts.expense.ExpensePackage.ExpensesActivity;
import com.wiselap.accounts.home_screen.HomeModule;
import com.wiselap.accounts.home_screen.Homepage;

import com.wiselap.accounts.SignIn.SignInModule;
import com.wiselap.accounts.users.AddUsersPackage.AddUserModule;
import com.wiselap.accounts.users.AddUsersPackage.AddUsersActivity;
import com.wiselap.accounts.users.UsersPackage.UserModule;
import com.wiselap.accounts.users.UsersPackage.UsersActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = SelectEntityModule.class)
    abstract SelectEntityActivity bindsSelectEntity();

    @ContributesAndroidInjector
    abstract SelectAccountActivity bindsSelectAccount();

    @ContributesAndroidInjector
    abstract ReportActivity bindsReportActivity();

    @ContributesAndroidInjector
    abstract ExpenseReportActivity bindsExpenseReportActivity();

    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract Homepage bindsHomepage();

    @ContributesAndroidInjector(modules = ExpenseModule.class)
    abstract ExpensesActivity bindsExpenses();

    @ContributesAndroidInjector(modules = SignInModule.class)
    abstract LoginActivity bindsGoogleSignInUsers();

    @ContributesAndroidInjector(modules = UserModule.class)
    abstract UsersActivity bindsUsers();


    @ContributesAndroidInjector(modules = PersonalModule.class)
    abstract OfficeActivity bindsOfficeActivity();

    @ContributesAndroidInjector(modules = PersonalModule.class)
    abstract PersonalActivity bindsPersonalActivity();



    @ContributesAndroidInjector
    abstract ExpenseReportDisplayActivity bindsExpenseReportDisplayActivity();

    @ContributesAndroidInjector
    abstract SalaryReportActivity bindsSalaryReportActivity();
    @ContributesAndroidInjector
    abstract SalaryReportDisplayActivity bindsSalaryReportDisplayActivity();
    @ContributesAndroidInjector
    abstract ConfigurationActivity bindsConfigurationActivity();
    @ContributesAndroidInjector(modules = ExpenseTypeModule.class)
    abstract ExpenseTypeActivity bindsExpenseTypeActivity();
    @ContributesAndroidInjector(modules = ApiModule.class)
    abstract AddExpenseTypeActivity bindsAddExpenseTypeActivity();

    @ContributesAndroidInjector(modules = AddUserModule.class)
    abstract AddUsersActivity bindsAddUsers();

    @ContributesAndroidInjector(modules = AddExpenseModule.class)
    abstract AddExpenseActivity bindsAddExpense();

    @ContributesAndroidInjector
    abstract ApplicationSettingActivity bindsApplicationSettingActivity();

    @ContributesAndroidInjector
    abstract FundTransferActivity bindsFundTransferActivity();

    @ContributesAndroidInjector(modules = FundUsersModule.class)
    abstract FundUsersActivity bindsFundUsersActivity();

    @ContributesAndroidInjector(modules = FundMethodModule.class)
    abstract FundTransferMethodActivity bindsFundTransferMethodActivity();

}
