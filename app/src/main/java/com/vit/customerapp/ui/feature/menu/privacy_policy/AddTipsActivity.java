package com.vit.customerapp.ui.feature.menu.privacy_policy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.vit.customerapp.R;
import com.vit.customerapp.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AddTipsActivity extends BaseActivity {

    public static void moveAddTipsActivity(Activity activity) {
        activity.startActivity(new Intent(activity, AddTipsActivity.class));
    }

    @BindView(R.id.input_custom_tip)
    EditText mInputCustomTip;

    @BindView(R.id.text_two_tips)
    TextView mTextTwoTips;

    @BindView(R.id.text_five_tips)
    TextView mTextFiveTips;

    @BindView(R.id.text_ten_tips)
    TextView mTextTenTips;

    @Override
    protected int getLayoutId() {
        return R.layout.add_tips_activity;
    }

    @Override
    protected int getTitleToolbarId() {
        return R.string.none;
    }

    @Override
    protected void initView() {

    }

    @OnClick(R.id.text_ten_tips)
    void onClickTenTips() {
        mTextTenTips.setSelected(true);
        mTextFiveTips.setSelected(false);
        mTextTwoTips.setSelected(false);
        mInputCustomTip.setSelected(false);
        mInputCustomTip.setFocusable(false);
        mInputCustomTip.clearFocus();
        dismissKeyboard(mInputCustomTip, this);
    }

    @OnClick(R.id.text_two_tips)
    void onClickTwoTips() {
        mTextTwoTips.setSelected(true);
        mTextFiveTips.setSelected(false);
        mTextTenTips.setSelected(false);
        mInputCustomTip.setSelected(false);
        mInputCustomTip.clearFocus();
        dismissKeyboard(mInputCustomTip, this);
    }

    @OnClick(R.id.text_five_tips)
    void onClickFiveTips() {
        mTextFiveTips.setSelected(true);
        mTextTenTips.setSelected(false);
        mTextTwoTips.setSelected(false);
        mInputCustomTip.setSelected(false);
        mInputCustomTip.clearFocus();
        dismissKeyboard(mInputCustomTip, this);
    }

    @OnClick(R.id.input_custom_tip)
    void onClickCustomTips() {
        mInputCustomTip.requestFocus();
        mInputCustomTip.setFocusable(true);
        mInputCustomTip.setFocusableInTouchMode(true);
        mInputCustomTip.setSelected(true);
        mTextFiveTips.setSelected(false);
        mTextTwoTips.setSelected(false);
        mTextTenTips.setSelected(false);
    }

    public static void dismissKeyboard(EditText editText, Context context) {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
