package com.joeykwok.yunposv1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.joeykwok.yunposv1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClearingActivity extends AppCompatActivity {

    @BindView(R.id.clearing_toolbar)
    Toolbar mToolbar;


    private double mTotal; //应收金额
    private double mDiscount; //优惠金额

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clearing);

        ButterKnife.bind(this);

        $getIntent();

        setupToolbar();

    }

    private void $getIntent() {
        Intent intent = getIntent();
        mTotal = intent.getDoubleExtra("total", 0);
        mDiscount = intent.getDoubleExtra("discount", 0);
        Log.d("Joey", "应收：" + mTotal + "，优惠：" + mDiscount);
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
