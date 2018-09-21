package com.vit.customerapp.ui.feature.verifyphone;

import com.vit.customerapp.data.model.request.VerifyPhoneRequest;
import com.vit.customerapp.data.model.request.VerifyPincodeRequest;
import com.vit.customerapp.data.model.response.BaseResponse;
import com.vit.customerapp.data.model.response.VerifyPhoneResponse;
import com.vit.customerapp.ui.base.BasePresenter;
import com.vit.customerapp.ui.base.BaseView;

public class VerifyPhoneContract {

    interface View extends BaseView<Presenter> {
        void onVerifyPhoneSuccess(VerifyPhoneResponse response);

        void onVerifyPincodeSuccess(BaseResponse response);

        void onVerifyPhoneError(Throwable t);

        void onVerifyPincodeError(Throwable t);
    }

    interface Presenter extends BasePresenter {
        void postVerifyPhone(VerifyPhoneRequest request);

        void postVerifyPincode(VerifyPincodeRequest request);
    }
}
