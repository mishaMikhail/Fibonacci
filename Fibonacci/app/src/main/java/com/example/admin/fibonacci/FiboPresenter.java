package com.example.admin.fibonacci;

import android.view.MenuItem;

public class FiboPresenter {
    private FiboModel model;
    private FiboView view;
    private int method;
    private boolean click = true;

    FiboPresenter (FiboView view){
        this.view = view;
        model = new FiboModel();
        setMethod(view.getString(R.string.descr_plain), 1);
    }

    public void count(int n){
        view.showProgress();
        view.showResult(view.getString(R.string.res_calc));
        view.setButtonText(view.getString(R.string.bt_cancel));
        view.setClickEnadled(false);

        model.count(new int[] {n, method}, new FiboModel.CountResultCallback(){
            @Override
            public void onCounted(String res) {
                if (res != null)
                    view.hideProgress();
                    view.showResult(res);
                    view.setButtonText(view.getString(R.string.bt_go));
                    view.setClickEnadled(true);
            }
        });
    }

    public void cancel(){
        model.cancel();
        view.showResult(view.getString(R.string.op_cancel));
        view.hideProgress();
        view.setButtonText(view.getString(R.string.bt_go));
        view.setClickEnadled(true);
    }

    public void setMethod(String title, int id){
        method = id;
        setDescription(title);
    }

    private void setDescription(String method){
        String descr = view.getString(R.string.descr) + " " + method;
        view.setDescription(descr);
    }

}
