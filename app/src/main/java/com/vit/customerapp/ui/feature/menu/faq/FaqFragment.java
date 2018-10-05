package com.vit.customerapp.ui.feature.menu.faq;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class FaqFragment extends BaseFragment {

    @BindView(R.id.image_plus)
    ImageView mImagePlus;

    @BindView(R.id.image_plus2)
    ImageView mImagePlus2;

    @BindView(R.id.image_plus3)
    ImageView mImagePlus3;

    @BindView(R.id.text_content_work)
    TextView mTextContentWork;

    @BindView(R.id.text_content_make)
    TextView mTextContentMake;

    @BindView(R.id.text_content_pay)
    TextView mTextContentPay;

    public static final String TAG = FaqFragment.class.getSimpleName();

    public static FaqFragment newInstance() {
        FaqFragment fragment = new FaqFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.faq_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnClick({R.id.image_plus, R.id.text_title_work})
    void onClickPlus() {
        handelDropDown(mImagePlus, mTextContentWork);
    }

    @OnClick({R.id.image_plus2, R.id.text_title_make})
    void onClickPlus2() {
        handelDropDown(mImagePlus2, mTextContentMake);
    }

    @OnClick({R.id.image_plus3, R.id.text_title_pay})
    void onClickPlus3() {
        handelDropDown(mImagePlus3, mTextContentPay);
    }

    private void handelDropDown(ImageView imageView, TextView textView) {
        if (imageView.isSelected()) {
            imageView.setSelected(false);
            textView.setVisibility(View.GONE);
        } else {
            imageView.setSelected(true);
            textView.setVisibility(View.VISIBLE);
        }
    }
}
