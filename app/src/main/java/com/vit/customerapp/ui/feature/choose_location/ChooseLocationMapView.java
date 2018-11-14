package com.vit.customerapp.ui.feature.choose_location;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;

public class ChooseLocationMapView extends MapView {
    public ChooseLocationMapView(Context context) {
        super(context);
    }

    public ChooseLocationMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChooseLocationMapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ChooseLocationMapView(Context context, GoogleMapOptions options) {
        super(context, options);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        /**
         * Request all parents to relinquish the touch events
         */
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
