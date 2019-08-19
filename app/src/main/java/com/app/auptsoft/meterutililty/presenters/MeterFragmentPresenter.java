package com.app.auptsoft.meterutililty.presenters;

import android.databinding.BaseObservable;

public class MeterFragmentPresenter extends BaseObservable {
    private boolean loading = false;

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }
}
