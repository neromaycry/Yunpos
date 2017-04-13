package com.joeykwok.yunposv1.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.speech.tts.UtteranceProgressListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.joeykwok.yunposv1.R;
import com.joeykwok.yunposv1.adapter.OrderListAdapter;
import com.joeykwok.yunposv1.common.HttpMethods;
import com.joeykwok.yunposv1.common.Utils;
import com.joeykwok.yunposv1.fragment.PriceDialogFragment;
import com.joeykwok.yunposv1.requestbean.rqSkuBean;
import com.joeykwok.yunposv1.responsebean.rpSkuBean;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.Subject;
import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.http.HTTP;

public class MainActivity extends BaseActivity implements PriceDialogFragment.PriceInputListener, OrderListAdapter.ListUpdateListener {

    @BindView(R.id.drawerlayout)
    FlowingDrawer mDrawer;
    @BindView(R.id.main_imgbg)
    ImageView mImgBg;
    @BindView(R.id.bar_input)
    TextView mInputBar;
    @BindView(R.id.num_0)
    FancyButton btnNum0;
    @BindView(R.id.num_1)
    FancyButton btnNum1;
    @BindView(R.id.num_2)
    FancyButton btnNum2;
    @BindView(R.id.num_3)
    FancyButton btnNum3;
    @BindView(R.id.num_4)
    FancyButton btnNum4;
    @BindView(R.id.num_5)
    FancyButton btnNum5;
    @BindView(R.id.num_6)
    FancyButton btnNum6;
    @BindView(R.id.num_7)
    FancyButton btnNum7;
    @BindView(R.id.num_8)
    FancyButton btnNum8;
    @BindView(R.id.num_9)
    FancyButton btnNum9;
    @BindView(R.id.num_dot)
    FancyButton btnNumDot;
    @BindView(R.id.btn_backspace)
    FancyButton btnBackspace;
    @BindView(R.id.btn_add)
    FancyButton btnAdd;
    @BindView(R.id.btn_clear)
    FancyButton btnClear;
    @BindView(R.id.btn_delete)
    FancyButton btnDelete;
    @BindView(R.id.btn_amount)
    FancyButton btnAmount;
    @BindView(R.id.btn_discount)
    FancyButton btnDiscount;
    @BindView(R.id.btn_dis_rate)
    FancyButton btnDisRate;
    @BindView(R.id.btn_dis_whole)
    FancyButton btnDiscountWhole;
    @BindView(R.id.btn_rate_whole)
    FancyButton btnRateWhole;
    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.order_list)
    RecyclerView mOrderListView;
    @BindView(R.id.tv_subtotal)
    TextView tvSubtotal;
    @BindView(R.id.tv_discount)
    TextView tvDiscount;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.btn_checkout)
    FancyButton btnCheckout;


    @OnClick(R.id.num_0)
    void num0() {
        inputNum("0");
    }

    @OnClick(R.id.num_1)
    void num1() {
        inputNum("1");
    }

    @OnClick(R.id.num_2)
    void num2() {
        inputNum("2");
    }

    @OnClick(R.id.num_3)
    void num3() {
        inputNum("3");
    }

    @OnClick(R.id.num_4)
    void num4() {
        inputNum("4");
    }

    @OnClick(R.id.num_5)
    void num5() {
        inputNum("5");
    }

    @OnClick(R.id.num_6)
    void num6() {
        inputNum("6");
    }

    @OnClick(R.id.num_7)
    void num7() {
        inputNum("7");
    }

    @OnClick(R.id.num_8)
    void num8() {
        inputNum("8");
    }

    @OnClick(R.id.num_9)
    void num9() {
        inputNum("9");
    }

    @OnClick(R.id.num_dot)
    void numDot() {
        String input = mInputBar.getText().toString();
        if (input.contains(".")) {
            return;
        }
        if (sb.length() == 0) {
            inputNum("0");
        }
        inputNum(".");
    }

    @OnClick(R.id.btn_add)
    void btnAdd() {
        Log.d("Joey", "length:" + sb.length());
        if (sb.length() != 0) {
            add();
            sb.setLength(0);
            mInputBar.setText("0");
        } else {
            Toast.makeText(MainActivity.this, "请输入商品编码", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_backspace)
    void btnBackspace() {
        if (sb.length() > 1) {
            sb.delete(sb.length() - 1, sb.length());
            mInputBar.setText(sb.toString().trim());
        } else if (sb.length() == 1) {
            sb.setLength(0);
            mInputBar.setText("0");
        }
    }

    @OnClick(R.id.btn_clear)
    void btnClear() {
        sb.setLength(0);
        mInputBar.setText("0");
    }

    @OnClick(R.id.btn_delete)
    void btnDelete() {
        if (mOrderList.size() == 0) {
            Utils.showToastShort(this, "当前订单无商品");
            return;
        }
        if (mOrderList.size() == 1) {
            mSubtotal = 0;
            mDiscount = 0;
            mTotal = 0;
            DecimalFormat df = new DecimalFormat("#0.00");
            tvSubtotal.setText(df.format(mSubtotal));
            tvDiscount.setText(df.format(mDiscount));
            tvTotal.setText(df.format(mTotal));
        }
        mOrderList.remove(mPostion);
        updateSelectedPosition(mOrderList);
    }

    @OnClick(R.id.btn_amount)
    void btnAmount() {
        if (mOrderList.size() == 0) {
            Utils.showToastShort(this, "当前订单无商品");
            return;
        }
        String input = mInputBar.getText().toString();
        double amount = Double.parseDouble(input);
        if (amount == 0) {
            Utils.showToastShort(this, "商品数量不能为零");
            return;
        }
        mOrderList.get(mPostion).setNum(amount);
        mAdapter.notifyDataSetChanged();
        sb.setLength(0);
        mInputBar.setText("0");
    }

    @OnClick(R.id.btn_discount)
    void btnDiscount() {
        if (mOrderList.size() == 0) {
            Utils.showToastShort(this, "当前订单无商品");
            return;
        }
        String input = mInputBar.getText().toString();
        double discount = Double.parseDouble(input);
        double price = mOrderList.get(mPostion).getPrice();
        if (price >= discount) {
            mOrderList.get(mPostion).setDiscount(discount);
            mAdapter.notifyDataSetChanged();
        } else {
            Utils.showToastShort(this, "优惠金额不能大于商品价格");
        }
        sb.setLength(0);
        mInputBar.setText("0");
    }

    @OnClick(R.id.btn_dis_rate)
    void btnDisRate() {
        String input = mInputBar.getText().toString();
        double rate = Double.parseDouble(input) / 100;
        double price = mOrderList.get(mPostion).getPrice();
        double discount = Utils.mul(price, 1 - rate);
        mOrderList.get(mPostion).setDiscount(discount);
        mAdapter.notifyDataSetChanged();
        sb.setLength(0);
        mInputBar.setText("0");
    }

    @OnClick(R.id.btn_dis_whole)
    void btnDisWhole() {
        String input = mInputBar.getText().toString();
        double discount = Double.parseDouble(input);
        calWholeDiscount(discount, "01");
        sb.setLength(0);
        mInputBar.setText("0");
    }

    @OnClick(R.id.btn_rate_whole)
    void btnRateWhole() {
        String input = mInputBar.getText().toString();
        double rate = Utils.div(Double.parseDouble(input), 100);
        calWholeDiscount(rate, "02");
        sb.setLength(0);
        mInputBar.setText("0");
    }

    @OnClick(R.id.btn_checkout)
    void btnCheckout() {
        if (mOrderList.size() == 0) {
            Utils.showToastShort(this, "当前订单为空，请先添加商品");
            return;
        }
        Intent intent = new Intent(MainActivity.this, ClearingActivity.class);
        intent.putExtra("total", mTotal);
        intent.putExtra("discount", mDiscount);
        intent.putExtra("subtotal", mSubtotal);
        startActivity(intent);
    }

    private SharedPreferences sp;
    private StringBuffer sb;
    private List<rpSkuBean.GoodsDetailBean> mOrderList;
    private OrderListAdapter mAdapter;

    private int mPostion;

    private Gson mGson;

    public static final int BRIGHTNESS = -70;

    private double mSubtotal = 0,
            mDiscount = 0,
            mTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupToolbar();

        setupDrawer();

        Utils.setBgBrightness(BRIGHTNESS, mImgBg);

        sp = getSharedPreferences("userlogin", Context.MODE_PRIVATE);

        sb = new StringBuffer();

        mGson = new Gson();

        setupOrderList();

    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_menu);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.toggleMenu();
            }
        });
    }

    private void setupDrawer() {
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == ElasticDrawer.STATE_CLOSED) {
                    Log.d("Joey", "Drawer STATE_CLOSED");
                }
            }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {
                Log.d("Joey", "openRatio=" + openRatio + " ,offsetPixels=" + offsetPixels);
            }
        });

    }

    private void setupOrderList() {
        mOrderList = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mOrderListView.setLayoutManager(layoutManager);

        mAdapter = new OrderListAdapter(MainActivity.this, mOrderList);

        mOrderListView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OrderListAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view) {
                int position = mOrderListView.getChildAdapterPosition(view);
            }

            @Override
            public void onItemLongClick(View view) {

            }

            @Override
            public void onItemClick(View view, LinearLayout layout, int position) {
                mPostion = position;
            }
        });
    }

    private void inputNum(String num) {
        sb.append(num.trim());
        mInputBar.setText(sb.toString().trim());
    }

    private void add() {
        Subject<rpSkuBean> subject = new Subject<rpSkuBean>() {
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
            protected void subscribeActual(Observer<? super rpSkuBean> observer) {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(rpSkuBean value) {
                Log.d("Joey", value.toString());
                if (value.getStatus().equals("00")) {
                    Log.d("Joey", value.getGoods_detail().toString());
                    int index = value.getGoods_detail().size() - 1;
                    rpSkuBean.GoodsDetailBean item = value.getGoods_detail().get(index);
                    if (item.getPrice_auto().equals("1")) {
                        showEditPriceDialog(item);
                    } else {
                        mOrderList.add(item);
                        updateSelectedPosition(mOrderList);
                    }

                } else {
                    Utils.showToastShort(MainActivity.this, value.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Joey", e.toString());
            }

            @Override
            public void onComplete() {

            }
        };

        HashMap<String, String> map = new HashMap<>();
        map.put("contract_code", sp.getString("contract_code", ""));
        map.put("cust_id", "*");
        map.put("goods_detail", mGson.toJson(mOrderList));
        map.put("medium_id", "*");
        map.put("medium_type", "*");
        map.put("skucode", mInputBar.getText().toString());
        map.put("subtotal_preferential", "1");

        HttpMethods.getInstance().sku(subject, map, sp.getString("token", null));
    }

    private void calWholeDiscount(Double rate, String subType) {
        Subject<rpSkuBean> subject = new Subject<rpSkuBean>() {
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
            protected void subscribeActual(Observer<? super rpSkuBean> observer) {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(rpSkuBean value) {
                Log.d("Joey", value.toString());
                if (value.getStatus().equals("00")) {
                    mOrderList.clear();
                    for (int i = 0; i < value.getGoods_detail().size(); i++) {
                        mOrderList.add(value.getGoods_detail().get(i));
                    }
                    Log.d("Joey", mOrderList.toString());
                    updateSelectedPosition(mOrderList);
                } else {
                    Utils.showToastShort(MainActivity.this, value.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Joey", e.toString());
            }

            @Override
            public void onComplete() {

            }
        };

        HashMap<String, String> map = new HashMap<>();
        map.put("contract_code", sp.getString("contract_code", ""));
        map.put("cust_id", "*");
        map.put("goods_detail", mGson.toJson(mOrderList));
        map.put("medium_id", "*");
        map.put("medium_type", "*");
        map.put("skucode", "*");
        map.put("subtotal_preferential", String.valueOf(rate));
        map.put("sub_type", subType);

        Log.d("Joey", map.toString());

        HttpMethods.getInstance().sku(subject, map, sp.getString("token", null));
    }

    public void showEditPriceDialog(rpSkuBean.GoodsDetailBean goodsDetailBean) {
        PriceDialogFragment dialog = new PriceDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", goodsDetailBean);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "priceDialog");
    }

    @Override
    public void onPriceInputComplete(rpSkuBean.GoodsDetailBean goodsDetailBean) {
        mOrderList.add(goodsDetailBean);
        updateSelectedPosition(mOrderList);
    }

    private void updateSelectedPosition(List<rpSkuBean.GoodsDetailBean> list) {
        mPostion = list.size() - 1;
        mAdapter.setIsClicks();
        mAdapter.notifyDataSetChanged();
        if (list.size() != 0) {
            mOrderListView.smoothScrollToPosition(list.size() - 1);
        }
    }

    @Override
    public void onListUpdated(List<rpSkuBean.GoodsDetailBean> list) {
        Utils.showToastShort(this, "list updated");
        Log.d("Joey", "size:" + list.size());
        mSubtotal = 0;
        mDiscount = 0;
        mTotal = 0;
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                rpSkuBean.GoodsDetailBean item = list.get(i);
                mSubtotal += Utils.mul(item.getPrice(), item.getNum());
                mDiscount += item.getDiscount();
                mTotal = Utils.sub(mSubtotal, mDiscount);
                Log.d("Joey", "小计：" + mSubtotal + "，优惠：" + mDiscount + ",总计：" + mTotal);
            }
        }
        Log.d("Joey", "小计：" + mSubtotal + "，优惠：" + mDiscount + ",总计：" + mTotal);
        DecimalFormat df = new DecimalFormat("#0.00");
        tvSubtotal.setText(df.format(mSubtotal));
        tvDiscount.setText(df.format(mDiscount));
        tvTotal.setText(df.format(mTotal));
    }
}
