package com.joeykwok.yunposv1.requestbean;

import com.joeykwok.yunposv1.common.skuBean;

import java.util.List;

/**
 * Created by gjwlg on 2017/3/30.
 */

public class rqSkuBean {

    /**
     * skucode : 0000030101
     * contract_code :
     * cust_id : *
     * medium_id : *
     * medium_type : *
     * goods_detail : []
     * subtotal_preferential : 1
     */

    private String skucode;
    private String contract_code;
    private String sub_type;
    private String cust_id;
    private String medium_id;
    private String medium_type;
    private int subtotal_preferential;
    private List<skuBean> goods_detail;

    public String getSkucode() {
        return skucode;
    }

    public void setSkucode(String skucode) {
        this.skucode = skucode;
    }

    public String getContract_code() {
        return contract_code;
    }

    public void setContract_code(String contract_code) {
        this.contract_code = contract_code;
    }

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getMedium_id() {
        return medium_id;
    }

    public void setMedium_id(String medium_id) {
        this.medium_id = medium_id;
    }

    public String getMedium_type() {
        return medium_type;
    }

    public void setMedium_type(String medium_type) {
        this.medium_type = medium_type;
    }

    public int getSubtotal_preferential() {
        return subtotal_preferential;
    }

    public void setSubtotal_preferential(int subtotal_preferential) {
        this.subtotal_preferential = subtotal_preferential;
    }

    public List<skuBean> getGoods_detail() {
        return goods_detail;
    }

    public void setGoods_detail(List<skuBean> goods_detail) {
        this.goods_detail = goods_detail;
    }

    @Override
    public String toString() {
        return "rqSkuBean{" +
                "skucode='" + skucode + '\'' +
                ", contract_code='" + contract_code + '\'' +
                ", sub_type='" + sub_type + '\'' +
                ", cust_id='" + cust_id + '\'' +
                ", medium_id='" + medium_id + '\'' +
                ", medium_type='" + medium_type + '\'' +
                ", subtotal_preferential=" + subtotal_preferential +
                ", goods_detail=" + goods_detail +
                '}';
    }
}
