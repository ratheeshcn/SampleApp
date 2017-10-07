package com.intigral.ui.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.intigral.R;
import com.intigral.databinding.ActivityHomeBinding;
import com.intigral.ui.model.activity.HomeViewModel;
import com.intigral.ui.util.instrumentation.ActivityInstrumentationProvider;

/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */
public class HomeActivity extends BaseActivity{
    private ActivityHomeBinding mBinding;
    private HomeViewModel mModel;
    private void bindViews() {

    }

    private void initializeRecyclerView() {
        mBinding.rvAdditional.setHasFixedSize(true);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        mBinding.setModel(mModel = new HomeViewModel(ActivityInstrumentationProvider.from(this)));
        mModel.initializeModels();
        bindViews();
        initializeRecyclerView();


    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mModel.onDestroy();
    }




}
