package com.vit.customerapp.ui.feature.rebook_technician;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.vit.customerapp.R;
import com.vit.customerapp.data.model.response.Technician;
import com.vit.customerapp.data.remote.ApiUtils;
import com.vit.customerapp.ui.base.BaseActivity;
import com.vit.customerapp.ui.feature.rebook_technician.adapter.RebookTechnicianAdapter;
import com.vit.customerapp.ui.feature.rebook_technician.listener.OnClickTechnicianItemClickListener;
import com.vit.customerapp.ui.feature.rebook_technician.technician_info.TechnicianInfoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RebookTechnicianActivity extends BaseActivity implements OnClickTechnicianItemClickListener {

    @BindView(R.id.list_technician)
    RecyclerView mRcvTechnician;

    private RebookTechnicianAdapter mAdapter;
    private List<Technician> mListTechnicians;

    public static void moveRebookTechnicianActivity(Activity activity) {
        Intent intent = new Intent(activity, RebookTechnicianActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activity.startActivity(intent);
//        activity.overridePendingTransition(0, 0);
    }

    @Override
    protected void initView() {
        getTechnician();
        mAdapter = new RebookTechnicianAdapter(this);
        initRcvTechnician();

        mListTechnicians = new ArrayList<Technician>() {{
            add(new Technician("https://znews-photo-td.zadn.vn/w1024/Uploaded/neg_rtlzofn/2018_08_18/34463086_255467705002447_4087292506113310720_n.jpg",
                    8.5, "Karimova", "Elina"));
            add(new Technician("https://znews-photo-td.zadn.vn/w1024/Uploaded/neg_rtlzofn/2018_08_18/34463086_255467705002447_4087292506113310720_n.jpg",
                    8.5, "Karimova", "Elina"));
            add(new Technician("https://znews-photo-td.zadn.vn/w1024/Uploaded/neg_rtlzofn/2018_08_18/34463086_255467705002447_4087292506113310720_n.jpg",
                    8.5, "Karimova", "Elina"));
            add(new Technician("https://znews-photo-td.zadn.vn/w1024/Uploaded/neg_rtlzofn/2018_08_18/34463086_255467705002447_4087292506113310720_n.jpg",
                    8.5, "Karimova", "Elina"));
        }};
        mAdapter.setTechniciansList(mListTechnicians);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.rebook_technician_activity;
    }

    @Override
    protected int getTitleToolbarId() {
        mImageToolbar.setVisibility(View.VISIBLE);
        return R.string.app_name;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.notify_menu, menu);
        return true;
    }

    @Override
    public void onClickTechnician() {
        TechnicianInfoActivity.moveTechnicianInfoActivity(RebookTechnicianActivity.this);
    }

    private void getTechnician() {
        ApiUtils.getAPIService().getTechnician().enqueue(new Callback<List<Technician>>() {
            @Override
            public void onResponse(Call<List<Technician>> call, Response<List<Technician>> response) {
                Toast.makeText(RebookTechnicianActivity.this, response.body().get(0).getFirstName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Technician>> call, Throwable t) {

            }
        });
    }

    private void initRcvTechnician() {
        mRcvTechnician.addItemDecoration(new DividerItemDecoration(mRcvTechnician.getContext(), DividerItemDecoration.VERTICAL));
        mRcvTechnician.setLayoutManager(new LinearLayoutManager(this));
        mRcvTechnician.setHasFixedSize(true);
        mRcvTechnician.setItemAnimator(null);
        mRcvTechnician.setAdapter(mAdapter);
    }


}
