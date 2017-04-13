package com.joeykwok.yunposv1.fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.joeykwok.yunposv1.R;
import com.joeykwok.yunposv1.responsebean.rpSkuBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class PriceDialogFragment extends DialogFragment {


    private rpSkuBean.GoodsDetailBean goodsDetailBean;

    public PriceDialogFragment() {

    }

    public interface PriceInputListener {
        void onPriceInputComplete(rpSkuBean.GoodsDetailBean goodsDetailBean);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        goodsDetailBean = (rpSkuBean.GoodsDetailBean) getArguments().get("item");
        View view = inflater.inflate(R.layout.fragment_price_dialog, null);
        final EditText etPrice = (EditText) view.findViewById(R.id.et_price);
        builder.setView(view)
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String price = etPrice.getText().toString();
                                if (price.equals("")) {
                                    Toast.makeText(getActivity(), "请输入单价", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                goodsDetailBean.setPrice(Integer.parseInt(price));
                                PriceInputListener listener = (PriceInputListener) getActivity();
                                listener.onPriceInputComplete(goodsDetailBean);
//                                Toast.makeText(getActivity(), "确定", Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("取消", null);

        return builder.create();
    }

}
