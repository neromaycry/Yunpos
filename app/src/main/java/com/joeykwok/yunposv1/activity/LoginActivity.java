package com.joeykwok.yunposv1.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.devspark.appmsg.AppMsg;
import com.joeykwok.yunposv1.R;
import com.joeykwok.yunposv1.common.HttpMethods;
import com.joeykwok.yunposv1.common.Utils;
import com.joeykwok.yunposv1.custom.MyEditText;
import com.joeykwok.yunposv1.responsebean.rpUserBean;
import com.kejiwen.commonutilslibrary.MD5Utils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.Subject;
import mehdi.sakout.fancybuttons.FancyButton;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_username)
    MyEditText etUsername;
    @BindView(R.id.et_password)
    MyEditText etPassword;
    @BindView(R.id.img_background)
    ImageView imgBackground;
    @BindView(R.id.btn_dologin)
    FancyButton btnLogin;
    @BindView(R.id.smooth_progress_bar)
    SmoothProgressBar spbar;

    @OnClick(R.id.btn_dologin)
    void btnDoLogin() {
        doLogin();
    }

    private static final int BRIGHTNESS = -80;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        Utils.setBgBrightness(BRIGHTNESS, imgBackground);
    }


    private void doLogin() {
        Subject<rpUserBean> subject = new Subject<rpUserBean>() {
            @Override
            public boolean hasObservers() {
                return false;
            }

            @Override
            public boolean hasThrowable() {
                return false;
            }

            @Override
            public boolean hasComplete() {
                return false;
            }

            @Override
            public Throwable getThrowable() {
                return null;
            }

            @Override
            protected void subscribeActual(Observer<? super rpUserBean> observer) {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(rpUserBean value) {
                Log.d("Joey", value.toString());
                spbar.progressiveStop();
                if (value.getStatus().equals("00")) {
                    SharedPreferences sp = getSharedPreferences("userlogin", Context.MODE_PRIVATE);
                    sp.edit()
                            .putString("token", value.getToken())
                            .putString("user_id", value.getUser_id())
                            .putString("user_name", value.getUser_name())
                            .putString("contract_code", value.getContract_code())
                            .putString("phone", value.getPhone())
                            .putString("rate_manage", value.getRate_manager())
                            .putString("contract_code", value.getContract_code())
                            .putString("worker_position", value.getWorker_position()).commit();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    AppMsg.makeText(LoginActivity.this, value.getMsg(), AppMsg.STYLE_ALERT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
                spbar.progressiveStop();
                AppMsg.makeText(LoginActivity.this, "网络请求超时，请检查网络连接和网关设置", AppMsg.STYLE_ALERT).show();
                Log.d("Joey", e.toString());
            }

            @Override
            public void onComplete() {
                Log.d("Joey", "Request Completed!");
            }
        };

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        AppMsg.Style style = new AppMsg.Style(AppMsg.LENGTH_SHORT, R.color.colorCancel);
        if (username.equals("")) {
            AppMsg.makeText(LoginActivity.this, "请输入用户名", style).show();
            return;
        }
        if (password.equals("")) {
            AppMsg.makeText(LoginActivity.this, "请输入密码", style).show();
            return;
        }

        password = MD5Utils.getMD5(password).toLowerCase();
        Log.d("Joey", "username:" + username + ",password:" + password);

        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("user_id", username);
        paramsMap.put("user_password", password);
        paramsMap.put("accredit_type", "00");
        if (spbar.getVisibility() == View.GONE) {
            spbar.setVisibility(View.VISIBLE);
        } else {
            spbar.progressiveStart();
        }
        HttpMethods.getInstance().login(subject, paramsMap);
    }


}
