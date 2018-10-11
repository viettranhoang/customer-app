package com.vit.customerapp.ui.feature.menu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseActivity;
import com.vit.customerapp.ui.feature.menu.account.AccountUserProfileFragment;
import com.vit.customerapp.ui.feature.menu.appointment.AppointmentFragment;
import com.vit.customerapp.ui.feature.menu.faq.FaqFragment;
import com.vit.customerapp.ui.feature.menu.home.HomeFragment;
import com.vit.customerapp.ui.feature.menu.notifications.NotificationsActivity;
import com.vit.customerapp.ui.feature.menu.privacy_policy.PrivacyPolicyFragment;
import com.vit.customerapp.ui.feature.menu.support.CustomerSupportFragment;
import com.vit.customerapp.ui.feature.menu.term_of_use.TermOfUseFragment;

import butterknife.BindView;

public class DashboardActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static void moveDashboardActivity(Activity activity) {
        Intent intent = new Intent(activity, DashboardActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    private FragmentManager fragmentManager;
    private Handler fragmentHandler;

    private ActionBarDrawerToggle mToggle;

    @Override
    protected void initView() {

        setUpDrawerLayout();

        fragmentManager = getSupportFragmentManager();
        fragmentHandler = new Handler();
        switchFragment(HomeFragment.newInstance(), HomeFragment.TAG, false, true);
        mNavigationView.setCheckedItem(R.id.nav_home);

        mNavigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void initActionBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dashboard;
    }

    @Override
    protected int getTitleToolbarId() {
        mImageToolbar.setVisibility(View.VISIBLE);
        return R.string.app_name;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_notify) {
            NotificationsActivity.moveNotificationsActivity(this);
        }

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                switchFragment(HomeFragment.newInstance(), HomeFragment.TAG, false, true);
                break;

            case R.id.nav_appointment:
                switchFragment(AppointmentFragment.newInstance(), AppointmentFragment.TAG, false, true);
                break;

            case R.id.nav_account:
                switchFragment(AccountUserProfileFragment.newInstance(), AccountUserProfileFragment.TAG, false, true);
                break;

            case R.id.nav_term:
                switchFragment(TermOfUseFragment.newInstance(), TermOfUseFragment.TAG, false, true);
                break;

            case R.id.nav_policy:
                switchFragment(PrivacyPolicyFragment.newInstance(), PrivacyPolicyFragment.TAG, false, true);
                break;

            case R.id.nav_faq:
                switchFragment(FaqFragment.newInstance(), FaqFragment.TAG, false, true);
                break;

            case R.id.nav_support:
                switchFragment(CustomerSupportFragment.newInstance(), CustomerSupportFragment.TAG, false, true);
                break;

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setUpDrawerLayout() {
        mToolbarTitle.setText(getTitleToolbarId());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mToggle.setDrawerIndicatorEnabled(false);
        mToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        mToggle.setHomeAsUpIndicator(R.drawable.ic_menu);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
    }

    public void switchFragment(Fragment fragment, String tag, boolean addToBackStack, boolean clearBackStack) {
        fragmentHandler.post(new RunFragment(fragment, tag, addToBackStack, clearBackStack));
    }

    private class RunFragment implements Runnable {
        Fragment bf;
        String tag;
        boolean addToBackStack;
        boolean clearBackStack;

        RunFragment(Fragment bf, String tag, boolean addToBackStack, boolean clearBackStack) {
            this.bf = bf;
            this.tag = tag;
            this.addToBackStack = addToBackStack;
            this.clearBackStack = clearBackStack;
        }

        public void run() {
            if (clearBackStack) {
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }

            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.layout_fragment, bf, tag);
            if (addToBackStack) {
                ft.addToBackStack(null);
            }
            ft.commit();
        }
    }
}
