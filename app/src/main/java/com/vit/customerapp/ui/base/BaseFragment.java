package com.vit.customerapp.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vit.customerapp.ui.feature.menu.activity.DashboardActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    public static final String TAG = BaseFragment.class.getSimpleName();

    private Unbinder unbinder;

    protected abstract int getLayoutId();

    public DashboardActivity mDashboardActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DashboardActivity) {
            this.mDashboardActivity = (DashboardActivity) context;
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof DashboardActivity) {
            this.mDashboardActivity = (DashboardActivity) activity;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container,false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }

    public void showToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int resourceId) {
        Toast.makeText(getActivity(), resourceId, Toast.LENGTH_SHORT).show();
    }


}
