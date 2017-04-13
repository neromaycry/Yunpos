package com.joeykwok.yunposv1.responsebean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gjwlg on 2017/3/30.
 */

public class rpSkuBean {


    /**
     * amount_payable : 0
     * goods_detail : [{"barcode":"0000030101","brand_id":"000001","disc_bundling":0,"disc_gift":0,"disc_manual":0,"disc_minus":0,"disc_odd_even":0,"disc_subtotal":0,"disc_vip":0,"discount":0,"goods_name":"测试商品","kind_id":"01","money":0,"num":1,"picturename":"*","plu_id":"0000030101","plu_trip":"0000030101","price":0,"price_auto":"1","price_type":"00","serial":"0","spec":"*","unit_id":"*","worker_id1":"*","worker_id2":"*","worker_id3":"*"}]
     * status : 00
     * sum_rounded : 0
     */

    private double amount_payable;
    private String status;
    private String msg;
    private double sum_rounded;
    private List<GoodsDetailBean> goods_detail;

    public double getAmount_payable() {
        return amount_payable;
    }

    public void setAmount_payable(double amount_payable) {
        this.amount_payable = amount_payable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public double getSum_rounded() {
        return sum_rounded;
    }

    public void setSum_rounded(double sum_rounded) {
        this.sum_rounded = sum_rounded;
    }

    public List<GoodsDetailBean> getGoods_detail() {
        return goods_detail;
    }

    public void setGoods_detail(List<GoodsDetailBean> goods_detail) {
        this.goods_detail = goods_detail;
    }

    @Override
    public String toString() {
        return "rpSkuBean{" +
                "amount_payable=" + amount_payable +
                ", status='" + status + '\'' +
                ", sum_rounded=" + sum_rounded +
                ", goods_detail=" + goods_detail +
                '}';
    }

    public static class GoodsDetailBean implements Serializable {
        /**
         * barcode : 0000030101
         * brand_id : 000001
         * disc_bundling : 0
         * disc_gift : 0
         * disc_manual : 0
         * disc_minus : 0
         * disc_odd_even : 0
         * disc_subtotal : 0
         * disc_vip : 0
         * discount : 0
         * goods_name : 测试商品
         * kind_id : 01
         * money : 0
         * num : 1
         * picturename : *
         * plu_id : 0000030101
         * plu_trip : 0000030101
         * price : 0
         * price_auto : 1
         * price_type : 00
         * serial : 0
         * spec : *
         * unit_id : *
         * worker_id1 : *
         * worker_id2 : *
         * worker_id3 : *
         */

        private String barcode;
        private String brand_id;
        private int disc_bundling;
        private int disc_gift;
        private int disc_manual;
        private int disc_minus;
        private int disc_odd_even;
        private double disc_subtotal;
        private int disc_vip;
        private double discount;
        private String goods_name;
        private String kind_id;
        private double money;
        private double num;
        private String picturename;
        private String plu_id;
        private String plu_trip;
        private double price;
        private String price_auto;
        private String price_type;
        private String serial;
        private String spec;
        private String unit_id;
        private String worker_id1;
        private String worker_id2;
        private String worker_id3;

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public int getDisc_bundling() {
            return disc_bundling;
        }

        public void setDisc_bundling(int disc_bundling) {
            this.disc_bundling = disc_bundling;
        }

        public int getDisc_gift() {
            return disc_gift;
        }

        public void setDisc_gift(int disc_gift) {
            this.disc_gift = disc_gift;
        }

        public int getDisc_manual() {
            return disc_manual;
        }

        public void setDisc_manual(int disc_manual) {
            this.disc_manual = disc_manual;
        }

        public int getDisc_minus() {
            return disc_minus;
        }

        public void setDisc_minus(int disc_minus) {
            this.disc_minus = disc_minus;
        }

        public int getDisc_odd_even() {
            return disc_odd_even;
        }

        public void setDisc_odd_even(int disc_odd_even) {
            this.disc_odd_even = disc_odd_even;
        }

        public double getDisc_subtotal() {
            return disc_subtotal;
        }

        public void setDisc_subtotal(double disc_subtotal) {
            this.disc_subtotal = disc_subtotal;
        }

        public int getDisc_vip() {
            return disc_vip;
        }

        public void setDisc_vip(int disc_vip) {
            this.disc_vip = disc_vip;
        }

        public double getDiscount() {
            return discount;
        }

        public void setDiscount(double discount) {
            this.discount = discount;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getKind_id() {
            return kind_id;
        }

        public void setKind_id(String kind_id) {
            this.kind_id = kind_id;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public double getNum() {
            return num;
        }

        public void setNum(double num) {
            this.num = num;
        }

        public String getPicturename() {
            return picturename;
        }

        public void setPicturename(String picturename) {
            this.picturename = picturename;
        }

        public String getPlu_id() {
            return plu_id;
        }

        public void setPlu_id(String plu_id) {
            this.plu_id = plu_id;
        }

        public String getPlu_trip() {
            return plu_trip;
        }

        public void setPlu_trip(String plu_trip) {
            this.plu_trip = plu_trip;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getPrice_auto() {
            return price_auto;
        }

        public void setPrice_auto(String price_auto) {
            this.price_auto = price_auto;
        }

        public String getPrice_type() {
            return price_type;
        }

        public void setPrice_type(String price_type) {
            this.price_type = price_type;
        }

        public String getSerial() {
            return serial;
        }

        public void setSerial(String serial) {
            this.serial = serial;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getUnit_id() {
            return unit_id;
        }

        public void setUnit_id(String unit_id) {
            this.unit_id = unit_id;
        }

        public String getWorker_id1() {
            return worker_id1;
        }

        public void setWorker_id1(String worker_id1) {
            this.worker_id1 = worker_id1;
        }

        public String getWorker_id2() {
            return worker_id2;
        }

        public void setWorker_id2(String worker_id2) {
            this.worker_id2 = worker_id2;
        }

        public String getWorker_id3() {
            return worker_id3;
        }

        public void setWorker_id3(String worker_id3) {
            this.worker_id3 = worker_id3;
        }

        @Override
        public String toString() {
            return "{" +
                    "barcode:'" + barcode + '\'' +
                    ", brand_id:'" + brand_id + '\'' +
                    ", disc_bundling:" + disc_bundling +
                    ", disc_gift:" + disc_gift +
                    ", disc_manual:" + disc_manual +
                    ", disc_minus:" + disc_minus +
                    ", disc_odd_even:" + disc_odd_even +
                    ", disc_subtotal:" + disc_subtotal +
                    ", disc_vip:" + disc_vip +
                    ", discount:" + discount +
                    ", goods_name:'" + goods_name + '\'' +
                    ", kind_id:'" + kind_id + '\'' +
                    ", money:" + money +
                    ", num:" + num +
                    ", picturename:'" + picturename + '\'' +
                    ", plu_id='" + plu_id + '\'' +
                    ", plu_trip='" + plu_trip + '\'' +
                    ", price=" + price +
                    ", price_auto='" + price_auto + '\'' +
                    ", price_type='" + price_type + '\'' +
                    ", serial='" + serial + '\'' +
                    ", spec='" + spec + '\'' +
                    ", unit_id='" + unit_id + '\'' +
                    ", worker_id1='" + worker_id1 + '\'' +
                    ", worker_id2='" + worker_id2 + '\'' +
                    ", worker_id3='" + worker_id3 + '\'' +
                    '}';
        }
    }
}
